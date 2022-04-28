package com.levil.design.factory;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.AbstractBuildBO;

import java.util.Map;

// 这个考虑不要做成接口
public interface Actuator<T extends AbstractBuildBO> {
    @SuppressWarnings("unchecked")
    default void build(T obj){
        // TODO 这个map考虑一下怎么管理 这样会不会有点丑陋
        Map<HandlerTypeEnum, Object> handlerMap = AbstractBuildHandler.handlerMap;

        Object o = handlerMap.get(getHandlerTypeEnum());
        if (o==null){
            throw new RuntimeException("没有这个handler");
        }
        //这里是判断抽象类 还是接口
        if (o instanceof BuildHandler){
            BuildHandler<T> handler = (BuildHandler<T>) o;
            handler.build(obj);
        }

    }
    HandlerTypeEnum getHandlerTypeEnum();
}
