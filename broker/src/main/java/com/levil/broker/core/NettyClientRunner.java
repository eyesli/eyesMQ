//package com.levil.broker.core;
//
//import com.levil.broker.config.ServerList;
//import com.levil.core.broker.BrokerServerMember;
//import com.levil.remoting.client.NettyClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//
//import java.util.List;
//@Order(2)
//public class NettyClientRunner implements CommandLineRunner{
//    @Autowired
//    private NettyClient client;
//    @Autowired
//    private ServerList serverList;
//    //在springboot启动之后执行
//    @Override
//    public void run(String... args) {
//        List<BrokerServerMember> serverList = this.serverList.getServerList();
//        for (BrokerServerMember sm : serverList) {
//            client.start(sm.getIp(),sm.getPort());
//        }
//    }
//}
