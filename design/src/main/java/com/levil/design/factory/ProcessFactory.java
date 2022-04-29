package com.levil.design.factory;

public interface ProcessFactory<T> {

    BuildActuator build(T obj);
}