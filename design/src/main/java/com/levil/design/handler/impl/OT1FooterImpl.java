package com.levil.design.handler.impl;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.core.constants.processEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class OT1FooterImpl extends AbstractBuildHandler {



    @Override
    public void build(Big big) {
        System.out.println("OT1重写或者是增强= " + big);
    }

    @Override
    public Integer getOrderType() {
        return OrderTypeEnum.ONE.getType();
    }

    @Override
    public HandlerType getHandlerType() {
        /**
         *  TODO 这里考虑是不是 重写的是哪部分的。考虑通过type区分,不然只有开发者自己知道是build的是什么。
         */
        return new HandlerType(HandlerTypeEnum.DEFAULT_1,true,getOrderType(), processEnum.Brazil);
    }

}
