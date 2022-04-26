package com.levil.eyesmq;

import com.levil.design.DesignApplication;
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
    public void testAdd() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }

    }
    @Test
    public void GenerateService() {
        Big big = new Big();
        generateService.generate(big);
    }
}
