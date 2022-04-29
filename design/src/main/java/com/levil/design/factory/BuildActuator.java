package com.levil.design.factory;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BuildActuator<T extends AbstractBuildBO> {

    private final Map<HandlerTypeEnum, Object> handlerMap = new ConcurrentHashMap<>();

    public void register(HandlerTypeEnum k,Object v){
        handlerMap.put(k,v);
    }

    public void build(T obj,HandlerTypeEnum handlerTypeEnum){
        Object o = handlerMap.get(handlerTypeEnum);
        if (o==null){
            throw new RuntimeException("没有这个handler");
        }
        //这里是判断抽象类 还是接口
        if (o instanceof BuildHandler){
            BuildHandler<T> handler = (BuildHandler<T>) o;
            handler.build(obj);
        }

    }
}
