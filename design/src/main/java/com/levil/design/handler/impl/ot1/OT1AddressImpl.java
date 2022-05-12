package com.levil.design.handler.impl.ot1;

import com.levil.design.core.constants.AsyncModeEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class OT1AddressImpl extends AbstractBuildHandler<Big> {
    @Override
    public Big build(Big big) {
        big.setString4("OT1AddressImpl");
        return big;
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_4;
    }

    @Override
    public AsyncModeEnum asyncMode() {
        return AsyncModeEnum.NO_ASYNC;
    }
}
