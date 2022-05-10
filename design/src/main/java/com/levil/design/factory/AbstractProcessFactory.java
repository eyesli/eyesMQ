package com.levil.design.factory;

import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public abstract class AbstractProcessFactory<T extends AbstractBuildBO> implements ProcessFactory<T>, CommandLineRunner {

    private final Map<HandlerGroupEnum, HandlerTypeEnum> defaultProcessMap = new LinkedHashMap<>();
    //这是一个带头结点的单链表
    private final HandlerNode<T> header = new HandlerNode<>();
    @Autowired
    private BuildStorage<T> buildStorage;

    @PostConstruct
    private void init() {
        this.buildStorage.registerFactory(getOrderType(), this);
    }

    @Override
    public void run(String... args) {
        //构造 成员变量 defaultProcessMap 和 header
        this.initDefaultCommonProcessData();
    }

    private void initDefaultCommonProcessData() {
        defaultProcessMap.put(HandlerTypeEnum.DEFAULT_1.getHandlerGroupEnum(), HandlerTypeEnum.DEFAULT_1);
        defaultProcessMap.put(HandlerTypeEnum.DEFAULT_2.getHandlerGroupEnum(), HandlerTypeEnum.DEFAULT_2);
        defaultProcessMap.put(HandlerTypeEnum.DEFAULT_3.getHandlerGroupEnum(), HandlerTypeEnum.DEFAULT_3);
        defaultProcessMap.put(HandlerTypeEnum.DEFAULT_4.getHandlerGroupEnum(), HandlerTypeEnum.DEFAULT_4);
        defaultProcessMap.put(HandlerTypeEnum.DEFAULT_5.getHandlerGroupEnum(), HandlerTypeEnum.DEFAULT_5);

        HandlerNode<T> linkedList = header;
        Map<HandlerGroupEnum, HandlerTypeEnum> map = new LinkedHashMap<>(this.defaultProcessMap);
        for (Map.Entry<HandlerGroupEnum, HandlerTypeEnum> entry : map.entrySet()) {
            BuildHandler<T> handler = this.buildStorage.getBuildHandler(entry.getValue());
            HandlerNode<T> handlerNode = new HandlerNode<>();
            handlerNode.setHandler(handler);
            handlerNode.setHandlerGroupEnum(entry.getKey());
            linkedList.setNext(handlerNode);
            linkedList = handlerNode;
        }
    }

    @Override
    public void build(T obj) {

        switch (this.getStructureType()) {
            case LINKED_LIST:
                this.pipelineLinkedList().exec(obj);
                break;
            case HASH_ORDER:
                this.hashOrder(obj);
                break;
            case HASH_DISORDER:
                //目前还没单线程快
                this.hashDisorder(obj);
                break;
            default:
                throw new RuntimeException("未定义");
        }

    }

    private void hashDisorder(T obj) {
        Map<HandlerGroupEnum, HandlerTypeEnum> maps = this.pipeLineHash();
        this.buildStorage.getBuildHandlers(new ArrayList<>(maps.values())).parallelStream().forEach(e -> e.build(obj));
        System.out.println("obj = " + obj);
    }

    private void hashOrder(T obj) {
        Map<HandlerGroupEnum, HandlerTypeEnum> map = this.pipeLineHash();
        for (Map.Entry<HandlerGroupEnum, HandlerTypeEnum> entry : map.entrySet()) {
            BuildHandler<T> buildHandler = this.buildStorage.getBuildHandler(entry.getValue());
            if (buildHandler.isAsync()) {
                //todo 部分异步异步等待，还是异步非等待
                System.out.println("异步执行" + buildHandler.getHandlerType());
            }
            buildHandler.build(obj);
        }
    }

    protected Map<HandlerGroupEnum, HandlerTypeEnum> pipeLineHash() {
        //修改的只是副本，copyOnWrite思想
        Map<HandlerGroupEnum, HandlerTypeEnum> map = new LinkedHashMap<>(defaultProcessMap);
        Map<HandlerGroupEnum, HandlerTypeEnum> handlerTypeEnumMap = this.replacePipeLine();
        if (!ObjectUtils.isEmpty(handlerTypeEnumMap)) {
            map.putAll(handlerTypeEnumMap);
        }
        return map;
    }

    protected HandlerNode<T> pipelineLinkedList() {
        //深拷贝一个链表
        HandlerNode<T> handlerNode = new HandlerNode<>(header);
        Map<HandlerGroupEnum, HandlerTypeEnum> map = this.replacePipeLine();
        if (ObjectUtils.isEmpty(map)) {
            return handlerNode;
        }
        HandlerNode<T> que = handlerNode.getNext();
        HandlerNode<T> pre = handlerNode;
        //todo 链表替换 双向链表会不会好一点while 会不会死循环，添加强制终止条件
        for (Map.Entry<HandlerGroupEnum, HandlerTypeEnum> entry : map.entrySet()) {
            while (que != null) {
                if (que.getHandlerGroupEnum() == entry.getKey()) {
                    HandlerNode<T> next = que.getNext();
                    HandlerNode<T> newNode = new HandlerNode<>();
                    newNode.setNext(next);
                    BuildHandler<T> buildHandler = this.buildStorage.getBuildHandler(entry.getValue());
                    newNode.setHandler(buildHandler);
                    newNode.setHandlerGroupEnum(entry.getKey());
                    pre.setNext(newNode);
                }
                que = que.getNext();
                pre = pre.getNext();
            }

        }

        return handlerNode;
    }

    protected Map<HandlerGroupEnum, HandlerTypeEnum> replacePipeLine() {
        return new LinkedHashMap<>();
    }

    public abstract OrderTypeEnum getOrderType();

    public abstract StructureTypeEnum getStructureType();


    @Getter
    @AllArgsConstructor
    enum StructureTypeEnum {
        LINKED_LIST("链表实现"), HASH_ORDER("hash实现"), HASH_DISORDER("hash实现，全部handler多线程执行");
        private final String desc;
    }
}
