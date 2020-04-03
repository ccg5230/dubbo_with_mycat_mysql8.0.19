package com.ccg.impl;

import com.ccg.ExerciseProperties;
import com.ccg.HelloStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @className HelloStarterImpl
 * @Description
 * @Author chungaochen
 * Date 2020/4/3 11:16
 * Version 1.0
 **/
public class HelloStarterImpl implements HelloStarter {
    private static final Logger log = LoggerFactory.getLogger(HelloStarterImpl.class);

    @Autowired
    ExerciseProperties exerciseProperties;

    @Override
    public void welcome() {
        String name = exerciseProperties.getName() == null ? "springboot2" : exerciseProperties.getName();
        System.out.println("=========================================================");
        log.info("Hello " + name +", Welcome spring boot starter !");
    }
}