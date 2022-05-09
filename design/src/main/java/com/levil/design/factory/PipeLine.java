package com.levil.design.factory;

import com.levil.design.handler.BuildHandler;

import java.util.List;

public interface PipeLine<T> {

    PipeLine<T>  createPipeline(List<BuildHandler<T>> buildHandler);
    void exec(T obj);
}
