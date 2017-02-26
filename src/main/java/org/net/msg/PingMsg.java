package org.net.msg;

import org.net.type.MsgType;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.po
 * 概要：心跳包
 */
public final class PingMsg implements Msg {
    private static final long serialVersionUID = 1L;
    @Override
    public final MsgType getType() {
        return MsgType.PING;
    }

}
