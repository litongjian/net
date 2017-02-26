package org.net.po;

import org.net.type.ProcState;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.po
 * 概要：处理数据对象
 */
public final class ProcessData {
    private ProcState state;
    private NetData data;

    public ProcState getState() {
        return state;
    }

    public void setState(ProcState state) {
        this.state = state;
    }

    public NetData getData() {
        return data;
    }

    public void setData(NetData data) {
        this.data = data;
    }
}
