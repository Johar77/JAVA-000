package com.johar.geektime.dubbohmilytccdemo.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: DynamicSource
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/22 11:13
 * @Since: 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSource {
    String name() default "";
}
