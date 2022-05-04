package com.levil.design.factory;

import com.levil.design.core.constants.ProcessEnum;

public interface ProcessFactory<T> {
    void build(T obj);
    ProcessEnum getProcess();
}