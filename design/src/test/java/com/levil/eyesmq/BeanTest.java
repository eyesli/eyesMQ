package com.levil.eyesmq;

import com.levil.design.DesignApplication;
import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.factory.Actuator;
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

import static com.levil.design.core.constants.HandlerGroupEnum.HEADER;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_1;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_2;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_3;

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
        System.out.println("address = " + HandlerGroupEnum.getHandlerTypeEnumList(HEADER));
        List<Actuator<Big>> objects = Lists.newArrayList(() -> DEFAULT_1, () -> DEFAULT_2,() -> DEFAULT_3);
        objects.parallelStream().forEach(e->e.build(new Big()));

    }
}
