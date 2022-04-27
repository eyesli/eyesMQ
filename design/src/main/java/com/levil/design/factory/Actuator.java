package com.levil.design.factory;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.processEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;

import java.util.Map;

public interface Actuator {
    default void build(BuildHandler c, Big big){
        // TODO 这个map考虑一下怎么管理 这样会不会有点丑陋
        Map<HandlerType, AbstractBuildHandler> handlerMap = AbstractBuildHandler.handlerMap;
        if (c == null){
            HandlerType handlerType = new HandlerType();
            handlerType.setHandlerTypeEnum(getHandlerTypeEnum());
            handlerType.setIsOverride(false);
            handlerType.setOrderType(0);
            handlerType.setProcess(processEnum.COMMON);
            handlerMap.get(handlerType).build(big);
        }else {
            handlerMap.get(c.getHandlerType()).build(big);
        }
    }
    HandlerTypeEnum getHandlerTypeEnum();
}
