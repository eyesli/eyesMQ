package com.levil.design.handler;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.pojo.Big;

public interface BuildHandler {

    void build(Big big);

    HandlerType getHandlerType();

    Integer getOrderType();



}
