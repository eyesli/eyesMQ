package com.levil.design.handler.impl.common;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class TopCommon extends AbstractBuildHandler<Big> {
    @Override
    public void build(Big big) {
        System.out.println("TopCommonBuild " + big);
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_1;
    }

    @Override
    public boolean isAsync() {
        return false;
    }
}
