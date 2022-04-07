package com.levil.remoting.handler;


import com.levil.remoting.message.RequestMessage;
import com.levil.remoting.message.ResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class RequestMessageHandler extends SimpleChannelInboundHandler<RequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) {


        ctx.writeAndFlush(new ResponseMessage(true, "登录成功",1));
    }
}
