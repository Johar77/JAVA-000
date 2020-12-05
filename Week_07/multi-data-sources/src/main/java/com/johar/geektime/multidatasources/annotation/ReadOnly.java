package com.johar.geektime.multidatasources.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: ReadOnly
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 20:40
 * @Since: 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadOnly {
    String name() default "";
}
