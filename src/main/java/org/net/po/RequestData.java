package org.net.po;

import com.alibaba.fastjson.JSONObject;
import org.net.msg.Msg;
import org.net.type.MsgType;
import org.net.utils.UserTools;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.po
 * <p>
 * 概要：请求消息
 */
public final class RequestData implements Msg{
    private static final long serialVersionUID = 1L;
    private String server;
    private String uri;
    private JSONObject param;

    public MsgType getType() {
        return MsgType.REQUEST;
    }

    public RequestData(String host,String uri,Object param){
        server = host;
        this.uri = uri;
        if (param!=null){
            this.param = UserTools.castToJSONObject(param);
        }
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public JSONObject getParam() {
        return param;
    }

    public void setParam(JSONObject param) {
        this.param = param;
    }
}
