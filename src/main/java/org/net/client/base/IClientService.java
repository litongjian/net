package org.net.client.base;

import org.net.expr.UCException;
import org.net.msg.Msg;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.client.base
 * 概要：客户端服务接口
 */
public interface IClientService {

    void processMsg(Msg msg)throws UCException;

}
