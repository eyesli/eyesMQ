package com.levil.remoting.handler;


import com.levil.remoting.message.LoginRequestMessage;
import com.levil.remoting.message.LoginResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) {
        String username = msg.getUsername();
        String password = msg.getPassword();

        ctx.writeAndFlush(new LoginResponseMessage(true, "登录成功"));
    }
}
