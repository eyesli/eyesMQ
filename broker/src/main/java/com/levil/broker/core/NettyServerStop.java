package com.levil.broker.core;

import com.levil.broker.naming.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyServerStop implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private NettyServer server;
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("ContextClosedEvent = " + event);
        server.shutDown();
    }
}
