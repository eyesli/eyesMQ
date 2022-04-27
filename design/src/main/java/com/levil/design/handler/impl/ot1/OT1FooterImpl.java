package com.levil.design.handler.impl.ot1;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.common.FooterCommon;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class OT1FooterImpl extends FooterCommon {


    @Override
    public void build(Big big) {
        System.out.println("OT1重写或者是增强= " + big);
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_5;
    }

}
