package org.net.client.boots;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.net.client.base.IServer;
import org.net.client.handler.CoreClientHandler;
import org.net.expr.UCException;
import org.net.msg.RequestMsg;

/**
 * 所属项目：net
 * 创建时间：2017/2/27.
 * 路径：org.net.client.boots
 * 概要：服务启动器
 */
public class ClientBoots implements IServer{
    private String authToken;
    private String name;
    private String host;
    private int port;
    private SocketChannel channel;
    private boolean needPingServer;

    @Override
    public void registerAuthToken(String token) {
        this.authToken = token;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isNeedPingServer() {
        return needPingServer;
    }

    public SocketChannel getChannel(){
        return channel;
    }

    public ClientBoots(String name,String host,int port,boolean needPingServer){
        this.name = name;
        this.host = host;
        this.port = port;
        this.needPingServer = needPingServer;
    }

    @Override
    public void sendRequestMsg(RequestMsg msg) throws UCException {
        if (authToken!=null)msg.setAuthToken(authToken);
        if(!channel.isWritable()){connect();}
        ChannelFuture f = channel.writeAndFlush(msg);
        if (!f.isSuccess())throw new UCException(9998);//消息发送失败
    }

    @Override
    public void connect() throws UCException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE,true);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                channel = socketChannel;
                ChannelPipeline pp = socketChannel.pipeline();
                if (needPingServer)pp.addLast(new IdleStateHandler(20,10,0));
                pp.addLast(new ObjectEncoder(),new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                pp.addLast(new CoreClientHandler());
            }
        });
        try {
            ChannelFuture f = b.connect(host,port).sync();
            if (f.isSuccess()){
                System.out.println("服务器："+host+":"+port+"连接成功");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new UCException(9999);
        }
    }

    @Override
    public void disconnect() throws UCException {
        channel.disconnect();
    }

    @Override
    public String toString() {
        return "服务器："+name+"IP："+host+"端口："+port;
    }
}
