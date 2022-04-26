package com.levil.design.handler.impl;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.handler.config.HandlerConfig;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service(HandlerConfig.OT1_FOOTER)
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
    public Integer getHandlerType() {
        return HandlerType.CUSTOM_TOP.getType();
    }

}
