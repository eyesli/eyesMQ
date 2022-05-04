package com.levil.design.factory;

import com.levil.design.core.constants.ProcessEnum;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractProcessFactory<T> implements ProcessFactory<T>{
    @Override
    public void build(T obj) {
        Pipeline<T> tPipeline = pipelineCreate();
        tPipeline.exec(obj);
    }
    public abstract Pipeline<T> pipelineCreate();

    public abstract ProcessEnum getProcess();
}
