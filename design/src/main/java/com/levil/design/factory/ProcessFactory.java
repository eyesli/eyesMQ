package com.levil.design.factory;

import com.levil.design.constants.OrderTypeEnum;

public interface ProcessFactory<T> {

    void invoicePrint(T obj);

    OrderTypeEnum getOrderType();
}