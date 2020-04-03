package dubbo.boot;

import com.ccg.HelloStarter;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubbo
@EnableHystrix
//@ComponentScan(basePackages = {"com.ccg","dubbo.boot"})
public class DubboProviderApplication implements CommandLineRunner {
    @Autowired
    HelloStarter starter;

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
        System.out.println("dubbo服务启动");
    }

    @Override
    public void run(String... args) throws Exception {
        starter.welcome();
    }
    //endregion
}
