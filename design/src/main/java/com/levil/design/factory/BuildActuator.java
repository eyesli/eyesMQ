package com.levil.design.factory;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BuildActuator<T extends AbstractBuildBO> {

    private final Map<HandlerTypeEnum, BuildHandler<T>> handlerMap = new ConcurrentHashMap<>();

    public void register(HandlerTypeEnum k,BuildHandler<T> v){
        handlerMap.put(k,v);
    }

    public void build(T obj,HandlerTypeEnum handlerTypeEnum){
        BuildHandler<T> handler = handlerMap.get(handlerTypeEnum);
        if (handler==null){
            throw new RuntimeException("没有这个handler");
        }
        handler.build(obj);
    }
}
