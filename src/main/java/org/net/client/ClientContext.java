package org.net.client;

import org.net.anno.ClientServiceClass;
import org.net.client.base.IClientService;
import org.net.client.base.IServer;
import org.net.client.boots.ClientBoots;
import org.net.utils.ClassSearcher;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.client
 * 概要：客户端上下文
 */
public final class ClientContext {
    static {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void init() throws IOException {
        config = new Properties();
        config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/client.properties"));
        route = new ServiceRoute();
    }
    private static Properties config;
    private static Map<String,IClientService> serviceMap = new ConcurrentHashMap<>();
    private static ServiceRoute route;
    private static void registerServices(){
        String pkName = config.getProperty("service.path");
        List<Class> classList = ClassSearcher.searchByPackage(pkName).stream().filter(c->c.getAnnotation(ClientServiceClass.class)!=null||IClientService.class.isAssignableFrom(c)).collect(Collectors.toList());
        classList.forEach(c->{
            ClientServiceClass csc = (ClientServiceClass) c.getAnnotation(ClientServiceClass.class);
            try {
                serverMap.put(csc.name(), (IServer) c.newInstance());
            } catch (InstantiationException | IllegalAccessException ignored) {
            }
        });
    }
    private static Map<String,IServer> serverMap = new ConcurrentHashMap<>();
    private static void registerServers(){
        Enumeration<?> items = config.propertyNames();
        while (items.hasMoreElements()){
            String name = items.nextElement().toString();
            if (name.startsWith("server.")){
                String p = config.getProperty(name);
                String[] arr = p.split(":");
                assert arr.length ==2;
                serverMap.put(name,new ClientBoots(name,arr[0],Integer.valueOf(arr[1]),true));
            }
        }
    }

    public static IClientService getService(String name){
        return serviceMap.get(name);
    }

    public static IServer getServer(String name){
        return serverMap.get(name);
    }

    public static ServiceRoute getRoute(){
        return route;
    }



}
