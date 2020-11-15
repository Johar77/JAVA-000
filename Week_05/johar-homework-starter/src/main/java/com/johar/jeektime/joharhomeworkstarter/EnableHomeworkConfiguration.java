package com.johar.jeektime.joharhomeworkstarter;

import java.lang.annotation.*;

/**
 * @ClassName: EnableHomeworkConfiguration
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 23:43
 * @Since: 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableHomeworkConfiguration {
}
