package org.net.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 所属项目：net
 * 创建时间：2017/2/26.
 * 路径：org.net.anno
 * 概要：客户端服务方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientServiceMethod {
    String name();
}
