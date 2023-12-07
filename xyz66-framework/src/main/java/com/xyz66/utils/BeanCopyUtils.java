package com.xyz66.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean拷贝工具类
 * 
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    /**
     * Bean拷贝
     * @param source 源对象
     * @param clazz 目标对象
     * @return 目标对象
     */
    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }
    
    /**
     * Bean拷贝-集合
     * @param list 源对象集合
     * @param clazz 目标对象
     * @return 目标对象集合
     */
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
