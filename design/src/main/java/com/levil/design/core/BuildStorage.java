package com.levil.design.core;

import com.google.common.collect.Lists;
import com.levil.design.constants.HandlerTypeEnum;
import com.levil.design.constants.OrderTypeEnum;
import com.levil.design.factory.ProcessFactory;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BuildStorage<T extends AbstractBuildBO> {

    private final Map<HandlerTypeEnum, BuildHandler<T>> handlerMap = new ConcurrentHashMap<>();
    private final Map<OrderTypeEnum, ProcessFactory<T>> orderTypeFactoryMap = new ConcurrentHashMap<>();

    public void register(HandlerTypeEnum k, BuildHandler<T> v) {
        handlerMap.put(k, v);
    }

    public List<BuildHandler<T>> getBuildHandlers(List<HandlerTypeEnum> keys) {

        List<BuildHandler<T>> objects = Lists.newArrayList();
        for (HandlerTypeEnum key : keys) {
            BuildHandler<T> tBuildHandler = handlerMap.get(key);
            objects.add(tBuildHandler);
        }
        return objects;
    }

    public BuildHandler<T> getBuildHandler(HandlerTypeEnum key) {
        return handlerMap.get(key);
    }

    public void registerFactory(OrderTypeEnum orderType, ProcessFactory<T> processFactory) {
        orderTypeFactoryMap.put(orderType, processFactory);
    }

    public ProcessFactory<T> getOrderTypeFactory(T key) {

        return orderTypeFactoryMap.get(OrderTypeEnum.getByType(key.getOrderType()));
    }
}
