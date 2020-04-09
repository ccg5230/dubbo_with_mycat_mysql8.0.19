package exercise.starter.ccg;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className ExerciseProperties
 * @Description
 * @Author chungaochen
 * Date 2020/4/3 11:49
 * Version 1.0
 **/
//@Component //推荐这样激活ConfigurationProperties
@ConfigurationProperties("exer.star")//属性前缀
public class ExerciseProperties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}