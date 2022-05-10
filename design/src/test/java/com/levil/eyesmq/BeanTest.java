package com.levil.eyesmq;

import com.levil.design.DesignApplication;
import com.levil.design.factory.DefaultProcessFactory;
import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import com.levil.design.service.Impl.GenerateServiceImpl;
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


    @Test
    public void GenerateService() {
        long startTime = System.currentTimeMillis();
        generateService.generate(new Big());
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        System.out.println("usedTime = " + usedTime);
//        List<HandlerTypeEnum> objects = Lists.newArrayList(DEFAULT_1,DEFAULT_2, DEFAULT_3);
////        objects.parallelStream().forEach(e-> buildStorage.build(new Big(),e));
    }
}
