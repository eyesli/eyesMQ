package com.levil.design.factory;

import com.google.common.collect.Lists;
import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public abstract class AbstractProcessFactory<T extends AbstractBuildBO> implements ProcessFactory<T>{



    @Autowired
    HandlerStorage<T> buildStorage;
    @Override
    public void build(T obj) {
        Map<HandlerGroupEnum, HandlerTypeEnum> handlerTypeEnumMap = this.pipeLine();

        List<BuildHandler<T>> buildHandlers = buildStorage.getBuildHandlers(Lists.newArrayList(handlerTypeEnumMap.values()));
        for (BuildHandler<T> buildHandler : buildHandlers) {
            if (buildHandler.isAsync()) {
                System.out.println("异步执行" + buildHandler.getHandlerType());
            }
            buildHandler.build(obj);
        }
    }

    public Map<HandlerGroupEnum,HandlerTypeEnum> pipeLine(){
        Map<HandlerGroupEnum, HandlerTypeEnum> map = new LinkedHashMap<>();
        map.put(HandlerGroupEnum.LOGO,HandlerTypeEnum.DEFAULT_1);
        map.put(HandlerGroupEnum.HEADER,HandlerTypeEnum.DEFAULT_2);
        return map;
    }

    public abstract OrderTypeEnum getOrderType();
}
