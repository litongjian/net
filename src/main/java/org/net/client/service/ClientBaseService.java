package org.net.client.service;

import org.net.anno.ClientServiceClass;
import org.net.anno.ClientServiceMethod;
import org.net.client.base.IClientService;
import org.net.expr.UCException;
import org.net.msg.Msg;

/**
 * 所属项目：net
 * 创建时间：2017/2/27.
 * 路径：org.net.client.service
 * 概要：客户基础服务
 */
@ClientServiceClass(name = "base")
public class ClientBaseService implements IClientService{
    @Override
    public void processMsg(Msg msg) throws UCException {

    }

    @ClientServiceMethod(name = "post_login")
    public void postLogin(String userName,String password){

    }
}
