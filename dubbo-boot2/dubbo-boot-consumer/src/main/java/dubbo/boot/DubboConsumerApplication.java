package dubbo.boot;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@EnableDubbo
@EnableHystrix
@EnableSwagger2
public class DubboConsumerApplication {
    ReentrantLock lock;
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
        System.out.println("=====================dubbo消费者启动完毕===============");
    }
}
