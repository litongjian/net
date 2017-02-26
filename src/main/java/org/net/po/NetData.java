package org.net.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;
import org.net.expr.UCException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.po
 * 概要：交互数据
 */
public final class NetData implements Serializable {
    private static final long serialVersionUID = 1L;
    private JSONObject jsonData;
    private String className;
    private NetData() {
    }

    public static NetData createByObject(@NotNull Object obj)  {
        NetData nd = new NetData();
        nd.className = obj.getClass().getName();
        nd.jsonData = (JSONObject) JSON.toJSON(obj);
        return nd;
    }

    public static NetData createByListObject(@NotNull List<Object> objects){
        JSONArray array = new JSONArray(objects);
        NetData nd = new NetData();
        if (objects.size()>0&&objects.get(0)!=null){
            nd.className = objects.get(0).getClass().getName();
        }
        nd.jsonData.put("list_data",array);
        return nd;
    }

    public static NetData createByJsonObject(@NotNull JSONObject object){
        NetData nd = new NetData();
        nd.jsonData = object;
        return nd;
    }

    public String getClassName() {
        return className;
    }

    public <T extends Serializable> List<T> getJavaListData(Class<T> dataType) throws UCException {
        if (className==null)throw new UCException(4);
        if (!dataType.getName().equals(className))throw new UCException(2);
        if (!Serializable.class.isAssignableFrom(dataType))throw new UCException(3);
        if (jsonData==null)return null;
        if (!jsonData.containsKey("list_data"))return null;
        List<T> list = new ArrayList<T>();
        JSONArray array = jsonData.getJSONArray("list_data");
        array.forEach(j->list.add(((JSONObject)j).toJavaObject(dataType)));
        return list;
    }

    public <T extends Serializable> T getJavaData(Class<T> dataType) throws UCException {
        if (className==null)throw new UCException(4);
        if (!dataType.getName().equals(className))throw new UCException(2);
        if (!Serializable.class.isAssignableFrom(dataType))throw new UCException(3);
        return jsonData == null ? null : jsonData.toJavaObject(dataType);
    }

    public JSONObject getJsonData() {
        return jsonData;
    }
}
