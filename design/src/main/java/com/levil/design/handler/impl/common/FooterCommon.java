package com.levil.design.handler.impl.common;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.processEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class FooterCommon extends AbstractBuildHandler {


    @Override
    public void build(Big big) {
        System.out.println("big setFooterImpl= " + big);
    }

    @Override
    public Integer getOrderType() {
        //0就是所有order
        return 0;
    }

    @Override
    public HandlerType getHandlerType() {
        return new HandlerType(HandlerTypeEnum.DEFAULT_2,false,getOrderType(), processEnum.Brazil);
    }
}
