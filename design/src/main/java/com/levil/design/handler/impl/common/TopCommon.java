package com.levil.design.handler.impl.common;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.core.constants.processEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class TopCommon extends AbstractBuildHandler {
    @Override
    public void build(Big big) {
        System.out.println("big setTopImpl= " + big);
    }

    @Override
    public OrderTypeEnum getOrderType() {
        //枚举
        return OrderTypeEnum.ZERO;
    }

    @Override
    public HandlerType getHandlerType() {
        return new HandlerType(HandlerTypeEnum.DEFAULT_1,false,getOrderType(), processEnum.COMMON);
    }
}
