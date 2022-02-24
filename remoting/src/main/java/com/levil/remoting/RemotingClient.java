package com.levil.remoting;

import com.levil.remoting.common.ActionType;

public interface RemotingClient{
    void start(String ip , int port, Boolean heart, ActionType actionType);
}