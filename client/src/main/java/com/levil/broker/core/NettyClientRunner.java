package com.levil.broker.core;


import com.levil.broker.naming.NettyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
@Slf4j
public class NettyClientRunner implements CommandLineRunner {
    private final NettyClient server;

    @Autowired
    public NettyClientRunner(NettyClient server) {
        this.server = server;
    }

    @Override
    public void run(String... args) {
        server.start();
    }
}
