package org.net.expr;

import org.net.utils.UserTools;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.expr
 * 概要：用户异常
 */
public final class UCException extends Exception {
    private static Map<String,String> errMap ;
    private String code;

    public UCException(int code){
        this.code = "e"+UserTools.leftPad('0',4,code);
        if (errMap==null) {
            try {
                reloadErrorMap();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重新加载错误信息列表
     * @throws IOException 加载错误
     */
    public static void reloadErrorMap() throws IOException {
        if (errMap==null){
            errMap = new ConcurrentHashMap<>();
        }else {
            errMap.clear();
        }
        Properties p = new Properties();
        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/error.properties"));
        p.stringPropertyNames().forEach(e->errMap.put(e,p.getProperty(e)));
    }

    @Override
    public final String getMessage() {
        return "处理失败，错误代码["+code+"] 错误信息：["+errMap.get(code)+"]";
    }

    public String getCode() {
        return code;
    }
}
