package com.levil.design.handler.impl.common;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.handler.config.HandlerConfig;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service(HandlerConfig.TOP_COMMON)
public class TopCommon extends AbstractBuildHandler {
    @Override
    public void build(Big big) {
        System.out.println("big setTopImpl= " + big);
    }

    @Override
    public Integer getOrderType() {
        //枚举
        return null;
    }

    @Override
    public Integer getHandlerType() {
        return HandlerType.DEFAULT_FOOTER.getType();
    }
}
