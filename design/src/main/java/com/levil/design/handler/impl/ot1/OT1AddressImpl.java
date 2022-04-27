package com.levil.design.handler.impl.ot1;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.core.constants.processEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class OT1AddressImpl extends AbstractBuildHandler {
    @Override
    public void build(Big big) {
        System.out.println("OT1重写Address " + big);
    }

    @Override
    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.ONE;
    }

    @Override
    public HandlerType getHandlerType() {
        return new HandlerType(HandlerTypeEnum.DEFAULT_16,true,getOrderType(), processEnum.Brazil);
    }
}
