package com.levil.broker.config;


import com.levil.core.broker.BrokerServerMember;
import com.levil.core.broker.DefaultIdGenerator;
import com.levil.core.broker.NodeState;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Component
public class ServerList {

    @Value("${broker.server}")
    private String configServer;
    @Value("${netty.port}")
    private int nettyPort;
    private List<String> ServerAndPortList;
    private List<BrokerServerMember> ServerList;
    private BrokerServerMember self;

    @SneakyThrows
    @PostConstruct
    private void init() {
        String[] split = this.configServer.split(",");
        List<String> serverAndPortList = Stream.of(split).collect(Collectors.toList());
        this.ServerAndPortList= serverAndPortList;
        this.ServerList= serverAndPortList.stream().map(e->{
            String[] strings = e.split(":");
            String ip = strings[0];
            int port = Integer.parseInt(strings[1]);
            BrokerServerMember brokerServerMember = new BrokerServerMember();
            brokerServerMember.setBrokerServerId(new DefaultIdGenerator(ip, port).generateId());
            brokerServerMember.setIp(ip);
            brokerServerMember.setPort(port);
            brokerServerMember.setNodeState(NodeState.DOWN);
            return brokerServerMember;
        }).collect(Collectors.toList());

        InetAddress localHost = InetAddress.getLocalHost();
        String ip  = localHost.getHostAddress();
        String id = new DefaultIdGenerator(ip, this.nettyPort).generateId();
        this.self=new BrokerServerMember(id,ip,this.nettyPort,NodeState.DOWN);
        System.out.println("PostConstruct = " + this);
    }
}