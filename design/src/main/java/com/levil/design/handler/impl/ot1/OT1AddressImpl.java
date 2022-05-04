package com.levil.design.handler.impl.ot1;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class OT1AddressImpl extends AbstractBuildHandler<Big> {
    @Override
    public void build(Big big) {
        System.out.println("OT1重写Address " + big);
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_16;
    }

    @Override
    public boolean isAsync() {
        return false;
    }
}
