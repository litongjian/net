package org.net.client.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import org.net.client.ClientContext;
import org.net.msg.Msg;
import org.net.msg.PingMsg;


/**
 * 所属项目：net
 * 创建时间：2017/2/27.
 * 路径：org.net.client.handler
 * 概要：客户端核心处理器
 */
public final class CoreClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Msg) {
            Msg m = ((Msg) msg);
            ClientContext.getRoute().routeMsg(m);
        }
        System.out.println("无效包");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.writeAndFlush(new PingMsg());
        }
    }

}
