package com.levil.design.factory;

import com.google.common.collect.Lists;
import com.levil.design.core.ReplaceObject;
import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.core.constants.StructureTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public abstract class AbstractProcessFactory<T extends AbstractBuildBO> implements ProcessFactory<T>{



    @Autowired
    private HandlerStorage<T> buildStorage;
    @Autowired
    private PipeLine<T> pipeLine;
    @Override
    public void build(T obj) {
        if (replacePipeLine().getStructureTypeEnum()==StructureTypeEnum.ARRAY){
            Map<HandlerGroupEnum, HandlerTypeEnum> handlerTypeEnumMap = this.pipeLine();

            List<BuildHandler<T>> buildHandlers = buildStorage.getBuildHandlers(Lists.newArrayList(handlerTypeEnumMap.values()));
            for (BuildHandler<T> buildHandler : buildHandlers) {
                if (buildHandler.isAsync()) {
                    System.out.println("异步执行" + buildHandler.getHandlerType());
                }
                buildHandler.build(obj);
            }
        }
        else {
            System.out.println("链表实现 = " + obj);
        }


    }


    public Map<HandlerGroupEnum,HandlerTypeEnum> pipeLine(){
        Map<HandlerGroupEnum, HandlerTypeEnum> map = new LinkedHashMap<>();
        map.put(HandlerGroupEnum.LOGO,HandlerTypeEnum.DEFAULT_1);
        map.put(HandlerGroupEnum.HEADER,HandlerTypeEnum.DEFAULT_2);
        ReplaceObject replaceObject = this.replacePipeLine();
        if (replaceObject.getStructureTypeEnum()==StructureTypeEnum.ARRAY){
            Map<HandlerGroupEnum, HandlerTypeEnum> handlerTypeEnumMap = replaceObject.getMap();
            if (!ObjectUtils.isEmpty(handlerTypeEnumMap)){
                map.putAll(handlerTypeEnumMap);
            }
        }
        return map;
    }

    public ReplaceObject replacePipeLine(){
        return new ReplaceObject(StructureTypeEnum.ARRAY, new LinkedHashMap<>()); +



    }

    public  boolean isOrderly(){
        return true;
    }

    public abstract OrderTypeEnum getOrderType();


}
