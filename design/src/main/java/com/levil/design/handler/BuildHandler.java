package com.levil.design.handler;

import com.levil.design.core.constants.AsyncModeEnum;
import com.levil.design.core.constants.HandlerTypeEnum;

public interface BuildHandler<T> {

    void build(T big);

    HandlerTypeEnum getHandlerType();

    AsyncModeEnum asyncMode();

}
