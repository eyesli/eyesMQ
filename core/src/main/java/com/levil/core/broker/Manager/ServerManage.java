package com.levil.core.broker.Manager;

import com.levil.core.broker.BrokerServerMember;

import java.util.List;
import java.util.Map;

public interface ServerManage {
    void register(List<BrokerServerMember> members);
    void registerSelf(BrokerServerMember self);
    void update(BrokerServerMember serverMember);
    List<BrokerServerMember> getAllServerMemberList();
    Map<String,BrokerServerMember> getAllServerMemberMap();
    BrokerServerMember getSelf();
}
