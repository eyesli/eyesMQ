package com.levil.design.handler;

import com.levil.design.pojo.Big;

public interface BuildHandler {

    void build(Big big);

    Integer getHandlerType();

    Integer getOrderType();

}
