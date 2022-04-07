package com.levil.broker.core;


import com.levil.broker.service.BrokerServer;
import com.levil.remoting.RemotingServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
@Slf4j
public class NettyServerRunner implements  CommandLineRunner{

    @Autowired
    private RemotingServer remotingServer;
    @Autowired
    private BrokerServer brokerServer;

    //springboot启动的时候 netty 启动并且做心跳检测
    @Override
    public void run(String... args) {
        this.remotingServer.start(state -> {
             if (state){
                 brokerServer.report();
             }
         });
    }
}
