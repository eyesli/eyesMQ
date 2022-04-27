package com.levil.design.handler.impl.abstra;

import com.levil.design.core.constants.HandlerType;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public  abstract class AbstractBuildHandler implements BuildHandler {

    public static final Map<HandlerType, AbstractBuildHandler> handlerMap = new ConcurrentHashMap<>();

//    @Autowired
//    CommonBuildService commonBuildService;

    //我还想过给实现类起别名来管理,但是我觉得这种方式更优雅
    @PostConstruct
    public void init() {
        handlerMap.put(getHandlerType(),this);
    }

//    //=================这里可能有很多看你拆得多细
//    protected void  footerCommonBuild(Big big){
//        commonBuildService.build();
//        System.out.println("公共的footerBuild FooterCommonBuild= "+big);
//    }
//    protected void  topCommonBuild(Big big){
//        System.out.println("公共的TopCommonBuild TopCommonBuild= "+big);
//        commonBuildService.build();
//    }
//    protected void  titleCommonBuild(Big big){
//        System.out.println("titleCommonBuild titleCommonBuild= "+big);
//        commonBuildService.build();
//    }
//    //=================这里可能有很多

    @Override
    public abstract void build(Big big);

    @Override
    public abstract Integer getOrderType();
    @Override
    public abstract HandlerType getHandlerType();


}
