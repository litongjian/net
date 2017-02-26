package org.net.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 所属项目：net
 * 创建时间：2017/2/27.
 * 路径：org.net.utils
 * 概要：类搜索器
 */
public final class ClassSearcher {

    private static File packageNameToFile(String name) throws UnsupportedEncodingException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(name.replaceAll("\\.","/"));
        String path = URLDecoder.decode(url.getFile(),"utf-8");
        return new File(path);
    }

    /**
     * @param name 包名
     * @return 包下所有的类 (不搜索子包）
     */
    public static List<Class> searchByPackage(String name){
        List<Class> classes = new LinkedList<>();
        File dir ;
        try {
            dir = packageNameToFile(name);
        } catch (UnsupportedEncodingException e) {return classes;}
        File[] files = dir.listFiles(f ->f.isFile()&&f.getName().endsWith(".class"));
        assert files != null;
        Arrays.stream(files).forEach(f ->{
            try {
                classes.add(Class.forName(name+"."+f.getName().substring(0,f.getName().length()-6)));
            } catch (ClassNotFoundException ignored) {
            }
        } );
        return classes;
    }

    /**
     * @param name 包名
     * @return 包下所有的类（搜索子包）
     */
    public static List<Class> searchByDeepPackage(String name){
        List<Class> classes = new LinkedList<>();
        File dir;
        try {
            dir = packageNameToFile(name);
        } catch (UnsupportedEncodingException e) {return classes;}
        File[] fs = dir.listFiles(f->f.isDirectory()||f.getName().endsWith(".class"));
        assert fs !=null;
        Arrays.stream(fs).forEach(f->{
                if (f.isDirectory()){
                    String temp = name+"."+f.getName();
                    classes.addAll(searchByDeepPackage(temp));
                }else {
                    try {
                        classes.add(Class.forName(name+"."+f.getName().substring(0,f.getName().length()-6)));
                    } catch (ClassNotFoundException ignored) {}
                }
        });
        return classes;
    }

}
