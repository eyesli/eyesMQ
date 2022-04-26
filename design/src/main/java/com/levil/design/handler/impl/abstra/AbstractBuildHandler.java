package com.levil.design.handler.impl.abstra;

import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public  abstract class AbstractBuildHandler implements BuildHandler {

    @Override
    public abstract void build(Big big);

    @Override
    public abstract Integer getType();

}
