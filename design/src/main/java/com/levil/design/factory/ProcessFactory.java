package com.levil.design.factory;

public interface ProcessFactory<T> {

    Actuator build(T obj);
}