package com.johar.jeektime.joharhomeworkstarter;

import com.johar.jeektime.joharhomeworkstarter.domain.Klass;
import com.johar.jeektime.joharhomeworkstarter.domain.School;
import com.johar.jeektime.joharhomeworkstarter.domain.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: HomeworkAutoConfiguration
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 23:57
 * @Since: 1.0.0
 */
@Configuration
@ConditionalOnBean(annotation = EnableHomeworkConfiguration.class)
@EnableConfigurationProperties({Klass.class, Student.class})
public class HomeworkAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    School getSchool() {
        return new School();
    }
}