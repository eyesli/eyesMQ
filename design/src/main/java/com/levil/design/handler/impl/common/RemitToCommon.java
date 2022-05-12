package com.levil.design.handler.impl.common;

import com.levil.design.constants.AsyncModeEnum;
import com.levil.design.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;


@Service
public class RemitToCommon extends AbstractBuildHandler<Big> {
    @Override
    public Big build(Big big) {
        big.setString5("RemitToCommon");
        return big;
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_5;
    }

    @Override
    public AsyncModeEnum asyncMode() {
        return AsyncModeEnum.NO_ASYNC;
    }
}
