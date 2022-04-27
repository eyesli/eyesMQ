package com.levil.design.factory;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.core.constants.processEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;

import java.util.Map;

public interface Actuator {
    default void build(HandlerType c, Big big){
        // TODO 这个map考虑一下怎么管理 这样会不会有点丑陋
        Map<HandlerType, AbstractBuildHandler> handlerMap = AbstractBuildHandler.handlerMap;
        if (c == null){
            HandlerType handlerType = new HandlerType();
            handlerType.setHandlerTypeEnum(getHandlerTypeEnum());
            handlerType.setIsOverride(false);
            handlerType.setOrderType(OrderTypeEnum.ZERO);
            handlerType.setProcess(processEnum.COMMON);
            handlerMap.get(handlerType).build(big);
        }else {
            //你重写的handler 和type 必须保持一致,不然你重写干什么
            if (c.getHandlerTypeEnum()!=getHandlerTypeEnum()) {
                throw new RuntimeException("HandlerType 不匹配");
            }
            handlerMap.get(c).build(big);
        }
    }
    HandlerTypeEnum getHandlerTypeEnum();
}
