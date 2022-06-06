package com.levil.design.factory;

import com.google.common.collect.Lists;
import com.levil.design.constants.HandlerGroupEnum;
import com.levil.design.constants.HandlerTypeEnum;
import com.levil.design.constants.OrderTypeEnum;
import com.levil.design.core.BuildStorage;
import com.levil.design.core.HandlerNode;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public abstract class AbstractProcessFactory<T extends AbstractBuildBO> implements ProcessFactory<T>, CommandLineRunner {

    private final Map<HandlerGroupEnum, HandlerTypeEnum> defaultProcessMap = new LinkedHashMap<>();
    //这是一个带头结点的单链表
    private final HandlerNode<T> header = new HandlerNode<>();
    @Autowired
    private BuildStorage<T> buildStorage;


    @Override
    public void run(String... args) {
        this.buildStorage.registerFactory(getOrderType(), this);
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
        header.setLength(this.defaultProcessMap.size());
    }

    @Override
    public void invoicePrint(T obj) {
        this.print(this.build(obj));
    }

    private void print(T build) {
        System.out.println("执行打印操作 = " + build);
    }

    public T build(T obj) {
        switch (this.getStructureType()) {
            case LINKED_LIST:
                return this.pipelineLinkedList().exec(obj);
            case HASH_ORDER:
                return this.hashOrder(obj);
            case HASH_DISORDER:
                //目前还没单线程快
                return this.hashDisorder(obj);
            default:
                throw new RuntimeException("未定义");
        }
    }

    private T hashDisorder(T obj) {
        Map<HandlerGroupEnum, HandlerTypeEnum> maps = this.pipeLineHash();
        this.buildStorage.getBuildHandlers(new ArrayList<>(maps.values())).parallelStream().forEach(e -> e.build(obj));
        return obj;
    }

    @SuppressWarnings("all")
    private T hashOrder(T obj) {
        Map<HandlerGroupEnum, HandlerTypeEnum> map = this.pipeLineHash();

        List<CompletableFuture> futureList = Lists.newArrayList();

        for (Map.Entry<HandlerGroupEnum, HandlerTypeEnum> entry : map.entrySet()) {
            BuildHandler<T> buildHandler = this.buildStorage.getBuildHandler(entry.getValue());
            CompletableFuture completableFuture = buildHandler.asyncModeActuator(obj);
            if (completableFuture != null) {
                futureList.add(completableFuture);
            }
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        return obj;
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
        int count = 0;

        while (que != null) {
            if (count >= handlerNode.getLength()) {
                throw new RuntimeException("死循环");
            }
            /**
             * 同一个组就替换
             */

            if (map.containsKey(que.getHandlerGroupEnum())) {
                HandlerNode<T> next = que.getNext();
                HandlerNode<T> newNode = new HandlerNode<>();
                newNode.setNext(next);
                BuildHandler<T> buildHandler = this.buildStorage.getBuildHandler(map.get(que.getHandlerGroupEnum()));
                newNode.setHandler(buildHandler);
                newNode.setHandlerGroupEnum(que.getHandlerGroupEnum());
                pre.setNext(newNode);
                map.remove(que.getHandlerGroupEnum());
            }
            que = que.getNext();
            pre = pre.getNext();
            count++;
        }
        /**
         * 不是同一个组就放到链表最后
         */
        for (Map.Entry<HandlerGroupEnum, HandlerTypeEnum> entry : map.entrySet()) {
            HandlerNode<T> newNode = new HandlerNode<>();
            BuildHandler<T> buildHandler = this.buildStorage.getBuildHandler(entry.getValue());
            newNode.setHandler(buildHandler);
            newNode.setHandlerGroupEnum(entry.getKey());
            pre.setNext(newNode);
            pre = pre.getNext();
            handlerNode.setLength(handlerNode.getLength() + 1);
        }
        return handlerNode;
    }

    protected Map<HandlerGroupEnum, HandlerTypeEnum> replacePipeLine() {
        return new LinkedHashMap<>();
    }

    public abstract OrderTypeEnum getOrderType();

    /**
     * 采用什么内部实现
     *
     * @return
     */
    public abstract StructureTypeEnum getStructureType();


    @Getter
    @AllArgsConstructor
    enum StructureTypeEnum {
        LINKED_LIST("链表实现"), HASH_ORDER("hash实现"), HASH_DISORDER("hash实现，全部handler多线程执行");
        private final String desc;
    }
}
