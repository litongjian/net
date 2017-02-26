package org.net.client.base;

import org.net.expr.UCException;
import org.net.msg.RequestMsg;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.client.base
 * 概要：服务端
 */
public interface IServer {

    void registerAuthToken(String token);

    void sendRequestMsg(RequestMsg msg)throws UCException;

    void connect()throws UCException;

    void disconnect()throws UCException;
}
