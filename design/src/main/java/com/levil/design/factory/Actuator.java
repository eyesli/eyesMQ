package com.levil.design.factory;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.AbstractBuildBO;

import java.util.Map;

public interface Actuator<T extends AbstractBuildBO> {
    default void build(T obj){
        // TODO 这个map考虑一下怎么管理 这样会不会有点丑陋
        Map<HandlerTypeEnum, AbstractBuildHandler> handlerMap = AbstractBuildHandler.handlerMap;

            AbstractBuildHandler<T> handler = handlerMap.get(getHandlerTypeEnum());
            if (handler==null){
                throw new RuntimeException("没有这个handler");
            }
            handler.build(obj);

    }
    HandlerTypeEnum getHandlerTypeEnum();
}
