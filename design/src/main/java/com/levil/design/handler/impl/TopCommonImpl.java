package com.levil.design.handler.impl;

import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class TopCommonImpl extends AbstractBuildHandler {
    @Override
    public void build(Big big) {
        System.out.println("big setTopImpl= " + big);
    }

    @Override
    public Integer getType() {
        //枚举
        return null;
    }
}
