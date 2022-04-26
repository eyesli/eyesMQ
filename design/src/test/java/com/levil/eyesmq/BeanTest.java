package com.levil.eyesmq;

import com.levil.design.DesignApplication;
import com.levil.design.handler.impl.OT1FooterImpl;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesignApplication.class)
public class BeanTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    GenerateService generateService;

    @Test
    public void testAdd() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }

    }
    @Autowired
    OT1FooterImpl ot1Footer;
    @Test
    public void GenerateService() {
        generateService.generate(new Big());
        Map<Integer, AbstractBuildHandler> handlerMap = ot1Footer.getHandlerMap();
        for (Map.Entry<Integer, AbstractBuildHandler> entry : handlerMap.entrySet()) {
            System.out.println("getKey = " + entry.getKey());
            System.out.println("getValue = " + entry.getValue());
        }
    }
}
