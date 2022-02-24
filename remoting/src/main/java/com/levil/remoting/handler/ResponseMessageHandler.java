package com.levil.remoting.handler;

import com.levil.remoting.message.ResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class ResponseMessageHandler extends SimpleChannelInboundHandler<ResponseMessage> {

    @Override

    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
        log.debug("{}", msg);

    }
}
