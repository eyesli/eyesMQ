package com.levil.eyesmq;

import com.levil.design.DesignApplication;
import com.levil.design.core.constants.HandlerType;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.core.constants.processEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.handler.impl.ot1.OT1FooterImpl;
import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesignApplication.class)
public class BeanTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    GenerateService generateService;

    @Test
    public void getBeanDefinitionNames() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
    @Test
    public void getBeanNamesForType() {
        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(BuildHandler.class);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
    @Autowired
    OT1FooterImpl ot1Footer;
    @Test
    public void GenerateService() {
        //HandlerTypeEnum.DEFAULT_16 对应的那个handler 就是重写 HandlerTypeEnum.DEFAULT_1 那个handler 的。
        //不够优雅，不太行
        HandlerTypeEnum.DEFAULT_1.build(new HandlerType(HandlerTypeEnum.DEFAULT_16,true, OrderTypeEnum.ONE, processEnum.Brazil),new Big());
        HandlerTypeEnum.DEFAULT_1.build(new Big());
    }
}
