package com.levil.design.factory;

import com.google.common.collect.Lists;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BuildStorage<T extends AbstractBuildBO> {

    private final Map<HandlerTypeEnum, BuildHandler<T>> handlerMap = new ConcurrentHashMap<>();

    public void register(HandlerTypeEnum k,BuildHandler<T> v){
        handlerMap.put(k,v);
    }
    public List<BuildHandler<T>> getBuildHandlers(List<HandlerTypeEnum> keys){

        List<BuildHandler<T>> objects = Lists.newArrayList();
        for (HandlerTypeEnum key : keys) {
            BuildHandler<T> tBuildHandler = handlerMap.get(key);
            objects.add(tBuildHandler);
        }
        return objects;

    }

//    public void build(T obj,HandlerTypeEnum handlerTypeEnum){
//        BuildHandler<T> handler = handlerMap.get(handlerTypeEnum);
//        if (handler==null){
//            throw new RuntimeException("没有这个handler");
//        }
//        handler.build(obj);
//    }
}
