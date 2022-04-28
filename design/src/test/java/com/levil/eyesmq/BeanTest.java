package com.levil.eyesmq;

import com.levil.design.DesignApplication;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.levil.design.core.constants.HandlerTypeEnum.*;

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
    @Test
    public void GenerateService() {
        List<HandlerTypeEnum> handlerTypeEnums = Lists.newArrayList(DEFAULT_1, DEFAULT_2, DEFAULT_3);

        handlerTypeEnums.parallelStream().forEach(e->e.build(new Big()));
    }
}
