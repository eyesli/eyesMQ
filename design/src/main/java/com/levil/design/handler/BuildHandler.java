package com.levil.design.handler;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.pojo.Big;

public interface BuildHandler {

    void build(Big big);

    HandlerTypeEnum getHandlerType();

}
