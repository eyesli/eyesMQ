package com.levil.design.handler.impl.abstra;

import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public  abstract class AbstractBuildHandler implements BuildHandler {

    private static final Map<Integer, AbstractBuildHandler> handlerMap = new ConcurrentHashMap<>();
    private static final Map<Integer, AbstractBuildHandler> OrderTypeMap = new ConcurrentHashMap<>();

    //我还想过给实现类起别名来管理,但是我觉得这种方式更优雅
    @PostConstruct
    public void init() {
        handlerMap.put(getHandlerType(), this);
        OrderTypeMap.put(getOrderType(), this);
    }

    @Override
    public abstract void build(Big big);

    @Override
    public abstract Integer getOrderType();
    @Override
    public abstract Integer getHandlerType();


}
