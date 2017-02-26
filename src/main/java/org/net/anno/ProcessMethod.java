package org.net.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 所属项目：net
 * 创建时间：2017/2/27.
 * 路径：org.net.anno
 * 概要：处理方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessMethod {
    String id();
    String success()default "";
    String failed()default "";
}
