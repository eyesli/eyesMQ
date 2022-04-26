package com.levil.design.handler.impl;

import com.levil.design.handler.config.HandlerConfig;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.handler.impl.common.FooterCommon;
import com.levil.design.pojo.Big;
import com.levil.design.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(HandlerConfig.OT1_FOOTER)
public class OT1FooterImpl extends AbstractBuildHandler {

    //用容器解耦，这里也可以继承
    @Autowired
    FooterCommon footerCommon;

    @Override
    public void build(Big big) {
        //这里可以考虑增强 还是重写
        footerCommon.build(big);
        System.out.println("OT1重写或者是增强= " + big);
    }

    @Override
    public Integer getType() {
        return Type.ONE.getType();
    }

}
