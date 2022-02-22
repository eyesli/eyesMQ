package com.levil.broker.core;


import com.levil.broker.config.ServerList;
import com.levil.broker.naming.NettyServer;
import com.levil.broker.service.BrokerServer;
import com.levil.core.broker.BrokerServerMember;
import com.levil.remoting.client.NettyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
@Order(1)
@Slf4j
public class NettyServerRunner implements  CommandLineRunner, ApplicationListener<WebServerInitializedEvent>{

    @Autowired
    private  NettyServer server;
    @Autowired
    private BrokerServer brokerServer;


   // onApplicationEvent 执行靠前Tomcat 发布事件执行
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        new Thread(() -> server.start()).start();
    }

    //springboot启动之后
    @Override
    public void run(String... args) {
        this.brokerServer.report();
    }
}
