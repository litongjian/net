package org.net.client.base;

import org.net.anno.ProcessMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.client.base
 * 概要：客户端服务接口
 */
public interface ClientProcess {

    default Map<String,Method> getProcessMethodMap(){
        Map<String,Method> methodMap = new ConcurrentHashMap<>();
        Arrays.stream(this.getClass().getDeclaredMethods()).forEach(m->{
            ProcessMethod pm = m.getAnnotation(ProcessMethod.class);
            if (pm!=null){
                methodMap.put(pm.id(),m);
            }
        });
        return methodMap;
    }

    default Map<String,ProcessMethod> getProcessMethodInfoMap(){
        Map<String,ProcessMethod> methodMap = new ConcurrentHashMap<>();
        Arrays.stream(this.getClass().getDeclaredMethods()).forEach(m->{
            ProcessMethod pm = m.getAnnotation(ProcessMethod.class);
            if (pm!=null)methodMap.put(pm.id(),pm);
        });
        return methodMap;
    }
}
