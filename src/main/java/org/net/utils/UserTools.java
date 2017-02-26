package org.net.utils;

import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.utils
 * 概要：工具类
 */
public final class UserTools {
    static {
        UID = UUID.randomUUID().toString().replaceAll("-","");
    }
    private static final String UID;

    /**
     * @return 当前机器唯一ID
     */
    public static String getCurrentUID(){
        return UID;
    }

    /**
     * @return 获取一个UUID
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * @param reg 填充字符
     * @param len 长度
     * @param arg 目标对象
     * @return 按给定长度用填充字符来填充
     */
    public static String leftPad(char reg,int len,@NotNull Object arg){
        StringBuffer sb = new StringBuffer();
        for (int i=0,t = len-arg.toString().length();i<t;i++){
            sb.append(reg);
        }
        sb.append(arg);
        return sb.toString();
    }

    /**
     * @param str 需要转换的字符串
     * @return 首字转换成母大写返回
     */
    public static String firstUpperCase(@NotNull String str){
        if (str.length()>1){
            return str.substring(0,1).toUpperCase()+str.substring(1);
        }
        if (str.length()==1){
            return str.toUpperCase();
        }
        return str;
    }

    /**
     * @param obj 需要转换的Object
     * @return JsonObject对象
     */
    public static JSONObject castToJSONObject(@NotNull Object obj){
        if (obj==null)return null;
        JSONObject json = new JSONObject();
        Field[] fields = obj.getClass().getFields();
        Arrays.asList(fields).forEach(f->{
            try {
                Method getter = obj.getClass().getDeclaredMethod("get"+firstUpperCase(f.getName()));
                json.put(f.getName(),getter.invoke(obj));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            }
        });
        return json;
    }
}

