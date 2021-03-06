package com.levil.remoting.netty;

import com.levil.core.broker.BrokerServerMember;
import com.levil.core.broker.DefaultIdGenerator;
import com.levil.core.broker.Manager.ServerManage;
import com.levil.core.broker.NodeState;
import com.levil.remoting.RemotingClient;
import com.levil.remoting.handler.ResponseMessageHandler;
import com.levil.remoting.message.PingMessage;
import com.levil.remoting.protocol.MessageCodecSharable;
import com.levil.remoting.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NettyClient implements RemotingClient {
    @Autowired
    private ServerManage serverManage;

    //todo 有没有线程安全问题 考虑管理一下Channel
    private  Channel channel = null;
//    private Map<String,Channel> channelMap =  new ConcurrentHashMap<>();
//    private Map<String,Channel> heartChannelMap =  new ConcurrentHashMap<>();

    @Override
    public Channel getChannel(String ip, int port, Boolean heart) {
            this.initChannel(ip, port, heart);
            return this.channel;
    }

    @Override
    public void heartBeat(String ip, Integer port) {
        this.getChannel(ip,port,true);
    }

    private void initChannel(String ip, int port, Boolean heart) {

        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        ResponseMessageHandler RESPONSE_MESSAGEHANDLER = new ResponseMessageHandler();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProcotolFrameDecoder());
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    // 用来判断是不是 读空闲时间过长，或 写空闲时间过长
                    // 10s 内如果没有向服务器写数据，会触发一个 IdleState#WRITER_IDLE 事件
                    ch.pipeline().addLast(new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));
                    // ChannelDuplexHandler 可以同时作为入站和出站处理器
                    ch.pipeline().addLast(new ChannelDuplexHandler() {
                        // 用来触发特殊事件
                        int count = 1;

                        @Override
                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
                            IdleStateEvent event = (IdleStateEvent) evt;
                            // 触发了写空闲事件
                            if (event.state() == IdleState.WRITER_IDLE) {
                                if (count >= 5 && heart) {
                                    log.info("心跳通过，关闭心跳");
                                    //记录日志
                                    group.shutdownGracefully();
                                } else {
                                    //记录日志
                                    log.info("5s 没有写数据了，发送一个心跳包");
                                    ctx.writeAndFlush(new PingMessage());
                                }
                                count++;
                            }
                        }
                    });
                    ch.pipeline().addLast(RESPONSE_MESSAGEHANDLER);
                }
            });
            ChannelFuture connect = bootstrap.connect(ip, port);
            connect.addListener((GenericFutureListener<ChannelFuture>) f -> {
                if (!f.isSuccess()) {
                    Throwable cause = f.cause();
                    group.shutdownGracefully();
                    log.info("连接失败!cause=>{}! doConnect => {}:{}", cause, ip, port);
                } else {
                    String generateId = new DefaultIdGenerator(ip, port).generateId();
                    Map<String, BrokerServerMember> allServerMemberMap = serverManage.getAllServerMemberMap();
                    BrokerServerMember brokerServerMember = allServerMemberMap.get(generateId);
                    if (brokerServerMember != null) {
                        brokerServerMember.setNodeState(NodeState.UP);
                        serverManage.update(brokerServerMember);
                        log.info("连接成功: localAddress => {} remoteAddress => {}", f.channel().localAddress(), f.channel().remoteAddress());
                    }
                    log.error("没有配置的服务器generateId=>{},服务器列表=>{}",generateId,allServerMemberMap);
             }
            });
             channel = connect.sync().channel();
             channel.closeFuture().addListener(future -> group.shutdownGracefully());
        } catch (Exception e) {
            log.error("client error", e);
        }
    }


}
