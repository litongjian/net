package org.net.msg;

import org.net.type.MsgType;
import org.net.utils.UserTools;

import java.io.Serializable;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.po
 * 概要：消息基类接口
 */
public interface Msg extends Serializable{

    MsgType type();

    default String id() {
        return UserTools.getCurrentUID();
    }

    String msgID();

    String authToken();
}
