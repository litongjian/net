package org.net.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 所属项目：net
 * 创建时间：2017/2/27.
 * 路径：org.net.anno
 * 概要：处理类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Processer {
    String id();
}
