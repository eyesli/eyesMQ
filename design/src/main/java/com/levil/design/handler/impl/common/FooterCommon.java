package com.levil.design.handler.impl.common;

import com.levil.design.core.constants.AsyncModeEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class FooterCommon extends AbstractBuildHandler<Big> {


    @Override
    public void build(Big big) {
        big.setString2("FooterCommon");
        System.out.println("big = " + big);
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_2;
    }

    @Override
    public AsyncModeEnum asyncMode() {
        return AsyncModeEnum.RUN_ASYNC;
    }
}
