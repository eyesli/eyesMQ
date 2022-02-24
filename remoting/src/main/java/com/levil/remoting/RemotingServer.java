package com.levil.remoting;

public interface RemotingServer {
    void start(CallBack callBack);
    void shutDown();

     interface CallBack {
        void setState( boolean state);
    }
}
