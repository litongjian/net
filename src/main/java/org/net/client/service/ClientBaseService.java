package org.net.client.service;

import org.net.anno.ProcessMethod;
import org.net.anno.Processer;
import org.net.client.base.ClientProcess;
import org.net.msg.ResponseMsg;

/**
 * 所属项目：net
 * 创建时间：2017/2/27.
 * 路径：org.net.client.service
 * 概要：客户基础服务
 */
@Processer(id="base")
public class ClientBaseService implements ClientProcess {


    @ProcessMethod(id = "login_callback",failed = "base@login")
    public void loginCallBack(ResponseMsg msg) {

    }

    @ProcessMethod(id = "login")
    public void login(){

    }

}
