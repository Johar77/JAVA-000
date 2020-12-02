package com.johar.jeektime.springmockdata.utils;

import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName: NewInstance
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/3 00:09
 * @Since: 1.0.0
 */
public class NewInstance {

    public static <T> T copyProperties(Map<String,Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance();
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            boolean flag = field.isAccessible();
            if (!flag){
                field.setAccessible(true);
            }

            if (map.containsKey(field.getName())){
                try {
                    field.set(obj, map.get(field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            field.setAccessible(flag);
        });


        return obj;
    }
}