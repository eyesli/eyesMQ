package com.levil.design.handler.impl.common;

import com.levil.design.handler.config.HandlerConfig;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service(HandlerConfig.FOOTER_COMMON)
public class FooterCommon extends AbstractBuildHandler {
    @Override
    public void build(Big big) {
        System.out.println("big setFooterImpl= " + big);
    }

    @Override
    public Integer getOrderType() {
        return null;
    }

    @Override
    public Integer getHandlerType() {
        return null;
    }
}
