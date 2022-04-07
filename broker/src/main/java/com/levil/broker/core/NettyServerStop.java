package com.levil.broker.core;

import com.levil.remoting.RemotingServer;
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
    private RemotingServer remotingServer;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("springboot 事件分发布");
        if (event instanceof ContextStartedEvent){
            log.info("springboot发布事件ContextStartedEvent:{}", event);
        }
        if (event instanceof ContextRefreshedEvent){
            log.info("springboot发布事件ContextRefreshedEvent:{}", event);
        }
        if (event instanceof ContextClosedEvent){
            remotingServer.shutDown();
            log.info("springboot发布事件ContextClosedEvent:{}", event);
        }
        if (event instanceof ContextStoppedEvent){
            log.info("springboot发布事件ContextStoppedEvent:{}", event);
        }
        if (event instanceof WebServerInitializedEvent){
            log.info("springboot发布事件WebServerInitializedEvent:{}", event);
        }
        if (event instanceof ApplicationReadyEvent){
            log.info("springboot发布事件ApplicationReadyEvent:{}", event);
        }
        log.info(">>>>>>>>>>>>>>>>:{}\n", event.getClass().getName());
    }
}
