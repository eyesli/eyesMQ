package com.levil.core.broker.Manager;

import com.levil.core.broker.BrokerServerMember;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ServerManagerImpl implements ServerManage{

    private BrokerServerMember self;
    private Map<String , BrokerServerMember> serverRegistry = new ConcurrentHashMap<>();

    @Override
    public void register(List<BrokerServerMember> members) {
        this.serverRegistry = members.stream().collect(Collectors.toMap(BrokerServerMember::getBrokerServerId, v -> v));
    }

    @Override
    public void registerSelf(BrokerServerMember self) {
        this.self=self;
    }

    @Override
    public void update(BrokerServerMember serverMember) {
        this.serverRegistry.put(serverMember.getBrokerServerId(),serverMember);
    }

    @Override
    public Map<String, BrokerServerMember> getAllServerMemberMap() {
        return this.serverRegistry;
    }

    @Override
    public List<BrokerServerMember> getAllServerMemberList() {
        return new ArrayList<>(serverRegistry.values());
    }

    @Override
    public BrokerServerMember getSelf() {
        return this.self;
    }
}
