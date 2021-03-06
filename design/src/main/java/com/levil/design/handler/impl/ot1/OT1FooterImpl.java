package com.levil.design.handler.impl.ot1;

import com.levil.design.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.common.FooterCommon;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class OT1FooterImpl extends FooterCommon {


    @Override
    public Big build(Big big) {
        big.setString5("OT1FooterImpl");
        return big;
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_6;
    }

}
