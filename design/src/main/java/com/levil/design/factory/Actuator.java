package com.levil.design.factory;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;

import java.util.Map;

public interface Actuator {
    default void build(Big big){
        // TODO 这个map考虑一下怎么管理 这样会不会有点丑陋
        Map<HandlerTypeEnum, AbstractBuildHandler> handlerMap = AbstractBuildHandler.handlerMap;

            AbstractBuildHandler handler = handlerMap.get(getHandlerTypeEnum());
            if (handler==null){
                throw new RuntimeException("没有这个handler");
            }
            handler.build(big);

    }
    HandlerTypeEnum getHandlerTypeEnum();
}
