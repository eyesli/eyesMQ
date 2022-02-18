package com.levil.broker.core;


import com.levil.broker.naming.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
@Slf4j
public class NettyServerRunner implements CommandLineRunner {
    private final NettyServer server;

    @Autowired
    public NettyServerRunner(NettyServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) {
        server.start();
    }
}
