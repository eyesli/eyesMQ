package com.levil.broker.service;

import com.levil.core.broker.BrokerServerMember;

public interface BrokerServer {
    void register(BrokerServerMember bs);
    void deregister();
    void update();
    void detail();
    void report();
}
