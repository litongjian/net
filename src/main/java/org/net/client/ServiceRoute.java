package org.net.client;

import org.net.client.base.ClientProcess;
import org.net.msg.Msg;
import org.net.type.MsgType;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所属项目：net
 * 创建时间：2017/2/27.
 * 路径：org.net.client
 * 概要：客户端服务路由
 */
public final class ServiceRoute {

    private static Map<String,String> taskPool = new ConcurrentHashMap<>();

    public void registerTask(String msgID,String uri){
        taskPool.put(msgID,uri);
    }

    public void unregisterTask(String msgID){
        taskPool.remove(msgID);
    }

    public void routeMsg(Msg msg){
        if (msg.type()== MsgType.REQUEST||msg.type()==MsgType.RESPONSE){
            String uri = taskPool.get(msg.msgID());
            assert uri!=null;
            String[] arr = uri.split("@");
            assert arr.length==2;
            ClientProcess cp = ClientContext.getService(arr[0]);
            Method method = cp.getProcessMethodMap().get(arr[1]);

        }
    }

}
