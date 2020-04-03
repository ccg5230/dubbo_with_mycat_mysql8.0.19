package com.ccg;

import com.ccg.impl.HelloStarterImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className ExerciesAutoConfiguration
 * @Description
 * @Author chungaochen
 * Date 2020/4/3 12:47
 * Version 1.0
 **/
@Configuration
@ConditionalOnClass //条件注解只有classpath存在指定class时，才会实例化bean
/* 不推荐这样激活ConfigurationProperties*/
@EnableConfigurationProperties(ExerciseProperties.class)
public class ExerciesAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HelloStarter helloStarter(){
        return new HelloStarterImpl();
    }
}