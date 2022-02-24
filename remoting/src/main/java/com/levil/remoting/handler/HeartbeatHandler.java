package com.levil.remoting.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeartbeatHandler {

    private IdleStateHandler idleStateHandler;
    private ChannelDuplexHandler channelDuplexHandler;

    public static IdleStateHandler getIdleStateHandler() {
        return new IdleStateHandler(20, 0, 0);
    }

    public static ChannelDuplexHandler getChannelDuplexHandler() {
        return new ChannelDuplexHandler() {
            // 用来触发特殊事件
            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{
                IdleStateEvent event = (IdleStateEvent) evt;
                // 触发了读空闲事件
                if (event.state() == IdleState.READER_IDLE) {
                    log.info("已经 25s 没有读到数据了");
                    // TODO: 02/22/22  读空闲达到了几次，也视为断开
                    ctx.channel().close();
                }
            }
        };
    }

}
