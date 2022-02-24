package com.levil.broker.core;

import com.levil.remoting.netty.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class NettyServerStop implements ApplicationListener {

    @Autowired
    private NettyServer server;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++");
        if (event instanceof ContextStartedEvent){
            log.info("================:{}", "ContextStartedEvent");
        }
        if (event instanceof ContextRefreshedEvent){
            log.info("================:{}", "ContextRefreshedEvent");
        }
        if (event instanceof ContextClosedEvent){
            server.shutDown();
            log.info("================:{}", "ContextClosedEvent");
        }
        if (event instanceof ContextStoppedEvent){
            log.info("================:{}", "ContextStoppedEvent");
        }
        if (event instanceof WebServerInitializedEvent){
            log.info("================:{}", "WebServerInitializedEvent");
        }
        if (event instanceof ApplicationReadyEvent){
            log.info("================:{}", "ApplicationReadyEvent");
        }
        log.info(">>>>>>>>>>>>>>>>:{}\n", event.getClass().getName());
    }
}
