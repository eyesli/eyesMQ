package com.levil.design.handler.impl.common;

import com.levil.design.core.constants.AsyncModeEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class TopCommon extends AbstractBuildHandler<Big> {
    @Override
    public Big build(Big big) {
        big.setString1("TopCommon");
        return big;

    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_1;
    }

    @Override
    public AsyncModeEnum asyncMode() {
        return AsyncModeEnum.NO_ASYNC;
    }
}
