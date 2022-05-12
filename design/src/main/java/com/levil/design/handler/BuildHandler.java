package com.levil.design.handler;

import com.levil.design.constants.AsyncModeEnum;
import com.levil.design.constants.HandlerTypeEnum;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("all")
public interface BuildHandler<T> {

    T build(T big);

    HandlerTypeEnum getHandlerType();

    AsyncModeEnum asyncMode();

    default CompletableFuture asyncModeActuator(T obj) {
        return this.asyncMode().asyncMethod(this, obj);
    }

}
