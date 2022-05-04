package com.levil.design.factory;

import com.levil.design.handler.BuildHandler;

public interface Pipeline<T> {

    void createPipeline(BuildHandler<T>... buildHandler);
    void exec(T obj);
}
