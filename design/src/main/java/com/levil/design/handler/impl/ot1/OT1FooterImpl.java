package com.levil.design.handler.impl.ot1;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.common.FooterCommon;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OT1FooterImpl extends FooterCommon {


    @Override
    public void build(Big big) {
        big.setString5("OT1FooterImpl");
        System.out.println("big = " + big);
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_6;
    }

}
