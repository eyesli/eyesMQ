package com.levil.broker.core;


import com.levil.broker.service.BrokerServer;
import com.levil.remoting.netty.NettyServer;
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
    private NettyServer server;
    @Autowired
    private BrokerServer brokerServer;

    @Override
    public void run(String... args) {
         server.start(state -> {
             if (state){
                 //Server之间的心跳检测
                 //再某个行为做完之后，在做心跳检测，或者定时任务去做心跳检测
                 brokerServer.report();
             }
         });
    }
}
