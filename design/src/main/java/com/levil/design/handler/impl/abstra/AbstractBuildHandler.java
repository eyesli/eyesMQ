package com.levil.design.handler.impl.abstra;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public  abstract class AbstractBuildHandler<T extends AbstractBuildBO> implements BuildHandler<T> {
 
    public static final Map<HandlerTypeEnum, Object> handlerMap = new ConcurrentHashMap<>();

    /**
     *  获取子类的所有泛型
     */
    private final List<Class<? extends AbstractBuildBO>> genericSubclass = new CopyOnWriteArrayList<>();

    //我还想过给实现类起别名来管理,但是我觉得这种方式更优雅
    @PostConstruct
    public void init() {
        //获取子类所有的泛型
        handlerMap.put(getHandlerType(),this);
        getGenericSubclass(getClass());
    }

    private void getGenericSubclass(Class clazz) {
        Type superclass = clazz.getGenericSuperclass();
        if(superclass instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type[] types = parameterizedType.getActualTypeArguments();
            genericSubclass.add((Class) types[0]);
        }
        if (superclass instanceof Class){
            //存在多级继承
            getGenericSubclass((Class)(superclass));
        }
        System.out.println("genericSubclass = " + genericSubclass);
    }


    @Override
    public abstract void build(T big);

    @Override
    public abstract HandlerTypeEnum getHandlerType();

}
