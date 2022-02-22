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
            BrokerServerMember brokerServerMember = new BrokerServerMember();
            brokerServerMember.setIp(strings[0]);
            brokerServerMember.setPort(Integer.parseInt(strings[1]));
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