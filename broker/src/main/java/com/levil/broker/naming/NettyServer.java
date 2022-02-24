package com.levil.broker.naming;


import com.levil.broker.config.ServerList;
import com.levil.core.broker.BrokerServerMember;
import com.levil.core.broker.Manager.ServerManage;
import com.levil.core.broker.NodeState;
import com.levil.remoting.RemotingServer;
import com.levil.remoting.handler.HeartbeatHandler;
import com.levil.remoting.handler.LoginRequestMessageHandler;
import com.levil.remoting.handler.QuitHandler;
import com.levil.remoting.protocol.MessageCodecSharable;
import com.levil.remoting.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyServer implements RemotingServer {

    @Autowired
    private ServerList serverList;

    @Autowired
    private ServerManage serverManage;

    private  NioEventLoopGroup boss;

    private  NioEventLoopGroup worker;


    @Override
    public void start(CallBack callBack) {

        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        LoginRequestMessageHandler LOGIN_HANDLER = new LoginRequestMessageHandler();
        QuitHandler QUIT_HANDLER = new QuitHandler();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new ProcotolFrameDecoder());
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    // 用来判断是不是 读空闲时间过长，或 写空闲时间过长
                    // 5s 内如果没有收到 channel 的数据，会触发一个 IdleState#READER_IDLE 事件
                    ch.pipeline().addLast(HeartbeatHandler.getIdleStateHandler());
                    // ChannelDuplexHandler 可以同时作为入站和出站处理器
                    ch.pipeline().addLast(HeartbeatHandler.getChannelDuplexHandler());
                    ch.pipeline().addLast(LOGIN_HANDLER);
                    ch.pipeline().addLast(QUIT_HANDLER);
                }
            });
            ChannelFuture sync = serverBootstrap.bind(this.serverList.getNettyPort()).sync();
            sync.addListener((GenericFutureListener<ChannelFuture>) future -> {
                log.info("Netty init over,result:{}", future.isSuccess());
                callBack.setState(future.isSuccess());
                if (future.isSuccess()){
                    BrokerServerMember self = serverManage.getSelf();
                    self.setNodeState(NodeState.UP);
                    serverManage.registerSelf(self);
                }
            });
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            callBack.setState(false);
            log.error("server error", e);
        } finally {
            callBack.setState(false);
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    @Override
    public void shutDown() {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }

}
