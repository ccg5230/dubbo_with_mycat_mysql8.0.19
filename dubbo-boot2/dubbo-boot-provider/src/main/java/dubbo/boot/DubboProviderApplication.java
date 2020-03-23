package dubbo.boot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDubbo
@EnableHystrix
public class DubboProviderApplication {

    //region Description
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
        System.out.println("dubbo服务启动");
    }
    //endregion
}
