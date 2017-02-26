package org.net.msg;

import org.net.po.NetData;
import org.net.type.MsgType;
import org.net.type.ProcState;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.msg
 * 概要：响应消息
 */
public final class ResponseMsg implements Msg{
    private static final long serialVersionUID = 1L;
    private String msgID;
    private ProcState state;
    private NetData data;

    private ResponseMsg(){}

    public static ResponseMsg createSuccess(){
        ResponseMsg msg = new ResponseMsg();
        msg.state = ProcState.SUCCESS;
        return msg;
    }

    public static ResponseMsg createFailed(){
        ResponseMsg msg = new ResponseMsg();
        msg.state = ProcState.FAILED;
        return msg;
    }

    public static ResponseMsg createError(){
        ResponseMsg msg = new ResponseMsg();
        msg.state = ProcState.ERROR;
        return msg;
    }

    @Override
    public final MsgType type() {
        return MsgType.RESPONSE;
    }

    public String msgID() {
        return msgID;
    }

    @Override
    public String authToken() {
        return null;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public ProcState getState() {
        return state;
    }

    public NetData getData() {
        return data;
    }

    public void setData(NetData data) {
        this.data = data;
    }
}
