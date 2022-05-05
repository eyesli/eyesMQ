package com.levil.design.factory;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractProcessFactory<T extends AbstractBuildBO> implements ProcessFactory<T>{

    @Autowired
    BuildStorage<T> buildStorage;
    @Override
    public void build(T obj) {
        List<HandlerTypeEnum> handlerTypes = pipelineCreate();
        List<BuildHandler<T>> buildHandlers = buildStorage.getBuildHandlers(handlerTypes);
        for (BuildHandler<T> buildHandler : buildHandlers) {
            buildHandler.build(obj);
        }
    }
    public abstract List<HandlerTypeEnum> pipelineCreate();

    public abstract OrderTypeEnum getOrderType();
}
