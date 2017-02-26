package org.net.msg;

import com.sun.istack.internal.NotNull;
import org.net.po.NetData;
import org.net.type.MsgType;
import org.net.utils.UserTools;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.msg
 * 概要：请求消息
 */
public final class RequestMsg implements Msg {
    private static final long serialVersionUID = 1L;
    private String serverID;
    private String msgID;
    private String uri;
    private String authToken;
    private NetData data;

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    @Override
    public String authToken() {
        return authToken;
    }

    public RequestMsg(@NotNull String serverID, @NotNull String uri){
        this.msgID = UserTools.createUUID();
        this.serverID = serverID;
        this.uri = uri;
    }

    public void setData(NetData data){
        this.data = data;
    }

    public NetData getData() {
        return data;
    }

    public String getServerID() {
        return serverID;
    }

    public String msgID() {
        if (msgID==null)msgID=UserTools.createUUID();
        return msgID;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public final MsgType type() {
        return MsgType.REQUEST;
    }
}
