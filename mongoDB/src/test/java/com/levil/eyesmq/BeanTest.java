package com.levil.eyesmq;

import com.levil.mongo.MongoApplication;

import com.levil.mongo.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoApplication.class)
public class BeanTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    UserDao userDao;


    @Test
    public void GenerateService() {
        long startTime = System.currentTimeMillis();
        String add = userDao.add();
        System.out.println("add = " + add);
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        System.out.println("usedTime = " + usedTime);
    }
}
