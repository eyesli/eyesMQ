package com.levil.design.handler.impl.abstra;

import com.levil.design.constants.AsyncModeEnum;
import com.levil.design.constants.HandlerTypeEnum;
import com.levil.design.core.BuildStorage;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.AbstractBuildBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public abstract class AbstractBuildHandler<T extends AbstractBuildBO> implements BuildHandler<T> {

    @Autowired
    private BuildStorage<T> buildStorage;

    /**
     * 获取子类的所有泛型
     */
    private final List<Class<? extends AbstractBuildBO>> genericSubclass = new CopyOnWriteArrayList<>();

    /**
     * 父类的@PostConstruct，所有子类都必须执行一次,通过spring反射实现
     * 所以定义权限是没用的。因为不是通过继承过去的
     * 源码地址
     * org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor#buildLifecycleMetadata(java.lang.Class)
     */
    @PostConstruct
    private void init() {
        buildStorage.register(getHandlerType(), this);
        //获取子类所有的泛型
        getGenericSubclass(getClass());
    }

    private void getGenericSubclass(Class clazz) {
        Type superclass = clazz.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type[] types = parameterizedType.getActualTypeArguments();
            genericSubclass.add((Class) types[0]);
        }
        if (superclass instanceof Class) {
            //存在多级继承
            getGenericSubclass((Class) (superclass));
        }
    }


    @Override
    public abstract T build(T big);

    @Override
    public abstract HandlerTypeEnum getHandlerType();

    @Override
    public abstract AsyncModeEnum asyncMode();

}
