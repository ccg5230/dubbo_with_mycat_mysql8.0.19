package dubbo.boot;

import exercise.starter.ccg.HelloStarter;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})    // 禁用数据源自动配置
@EnableDubbo
@EnableHystrix
@EnableSwagger2
//@MapperScan("dubbo.boot.dao.mapper")
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
}
