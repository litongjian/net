package org.net.client;

import org.net.client.base.IClientService;
import org.net.expr.UCException;
import org.net.msg.Msg;
import org.net.type.MsgType;

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

    public void routeMsg(Msg msg){
        if (msg.type()== MsgType.REQUEST||msg.type()==MsgType.RESPONSE){
            String uri = taskPool.get(msg.msgID());
            assert uri!=null;
            String[] arr = uri.split("@");
            assert arr.length==2;
            IClientService service = ClientContext.getService(arr[0]);
            assert service !=null;
            try {
                service.processMsg(msg);
            } catch (UCException e) {
                e.printStackTrace();
            }
        }
    }

}
