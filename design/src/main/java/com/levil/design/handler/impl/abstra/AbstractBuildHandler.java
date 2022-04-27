package com.levil.design.handler.impl.abstra;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public  abstract class AbstractBuildHandler implements BuildHandler {

    public static final Map<HandlerType, AbstractBuildHandler> handlerMap = new ConcurrentHashMap<>();


    //我还想过给实现类起别名来管理,但是我觉得这种方式更优雅
    @PostConstruct
    public void init() {
        handlerMap.put(getHandlerType(),this);
    }

    @Override
    public abstract void build(Big big);

    @Override
    public abstract OrderTypeEnum getOrderType();
    @Override
    public abstract HandlerType getHandlerType();


}
