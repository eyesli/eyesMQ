package com.levil.remoting;

import io.netty.channel.Channel;

public interface RemotingClient{
    Channel getChannel(String ip, int port, Boolean heart);

    void heartBeat(String ip, Integer port);
}