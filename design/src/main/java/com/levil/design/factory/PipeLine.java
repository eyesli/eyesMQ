package com.levil.design.factory;

import com.levil.design.handler.BuildHandler;

public interface PipeLine<T> {

    void createPipeline(BuildHandler<T>... buildHandler);
    void exec(T obj);
}
