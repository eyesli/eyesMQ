package com.levil.remoting.netty;


import com.levil.core.broker.BrokerServerMember;
import com.levil.core.broker.DefaultIdGenerator;
import com.levil.core.broker.Manager.ServerManage;
import com.levil.core.broker.NodeState;
import com.levil.remoting.RemotingClient;
import com.levil.remoting.common.ActionType;
import com.levil.remoting.message.ResponseMessage;
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
    @Override
    public void start(String ip, int port, Boolean heart, ActionType actionType) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();

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
                                    group.shutdownGracefully();
                                } else {
                                    log.info("5s 没有写数据了，发送一个心跳包");
                                    ctx.writeAndFlush(new PingMessage());
                                }
                                count++;
                            }
                        }
                    });
                    ch.pipeline().addLast("client handler", new ChannelInboundHandlerAdapter() {
                        // 接收响应消息
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            log.debug("msg: {}", msg);
                            if ((msg instanceof ResponseMessage)) {
                                ResponseMessage response = (ResponseMessage) msg;
                                if (response.isSuccess()) {
                                    // 如果登录成功
                                }
                            }
                        }

                        // 在连接建立后触发 active 事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) {
//                                    String[] s = command.split(" ");
//                                    switch (s[0]) {
//                                        case "send":
//                                            ctx.writeAndFlush(null);
//                                            break;
//                                        case "quit":
//                                            ctx.channel().close();
//                                    }

                        }

                        // 在连接断开时触发
                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            log.debug("连接已经断开，按任意键退出..");
                        }

                        // 在出现异常时触发
                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            log.debug("XXX连接已经断开，按任意键退出..{}", cause.getMessage());
                        }
                    });
                }
            });
            ChannelFuture connect = bootstrap.connect(ip, port);
            connect.addListener((GenericFutureListener<ChannelFuture>) f -> {
                if (!f.isSuccess()) {
                    Throwable cause = f.cause();
                    log.info("连接失败!cause=>{}! doConnect => {}:{}", cause, ip, port);
                } else {
                    String generateId = new DefaultIdGenerator(ip, port).generateId();
                    Map<String, BrokerServerMember> allServerMemberMap = serverManage.getAllServerMemberMap();
                    BrokerServerMember brokerServerMember = allServerMemberMap.get(generateId);
                    brokerServerMember.setNodeState(NodeState.UP);
                    serverManage.update(brokerServerMember);
                    log.info("连接成功: localAddress => {} remoteAddress => {}", f.channel().localAddress(), f.channel().remoteAddress());
                }
            });
            Channel channel = connect.sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("client error", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
