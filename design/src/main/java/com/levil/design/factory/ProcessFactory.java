package com.levil.design.factory;

import com.levil.design.core.constants.OrderTypeEnum;

public interface ProcessFactory<T> {
    void build(T obj);

    OrderTypeEnum getOrderType();
}