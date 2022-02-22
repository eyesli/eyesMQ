package com.levil.broker.service.Impl;

import com.levil.broker.config.ServerList;
import com.levil.broker.service.BrokerServer;
import com.levil.core.broker.BrokerServerMember;
import com.levil.remoting.client.NettyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BrokerServerImpl implements BrokerServer {

    @Autowired
    private ServerList serverList;
    @Autowired
    private NettyClient client;

    @Override
    public void register(BrokerServerMember bs) {

    }

    @Override
    public void deregister() {

    }

    @Override
    public void update() {

    }

    @Override
    public void detail() {

    }

    @Override
    public void report() {
        List<BrokerServerMember> serverList = this.serverList.getServerList();
        for (BrokerServerMember sm : serverList) {
            new Thread(() -> client.start(sm.getIp(),sm.getPort())).start();
        }
    }
}
