package dubbo.boot.config;

import dubbo.boot.facade.UserService;
import dubbo.boot.service.impl.OrderServiceImpl;
import org.apache.dubbo.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @className DubboConfig
 * @Description
 * @Author chungaochen
 * Date 2020/3/16 17:02
 * Version 1.0
 **/
//@Configuration
public class DubboConsumerConfig {

    /** 设置应用名称 **/
    @Bean
    @SuppressWarnings("UnnecessaryLocalVariable")
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig("dubbo-boot-consumer");
        return config;
    }

    /**  申明注册中心 **/
    @Bean
    @SuppressWarnings("UnnecessaryLocalVariable")
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.1.115:2181?backup=192.168.1.113:2181,192.168.1.114:2181");
        return registryConfig;
    }

    /** 引用UserService服务 */
    @Bean
    public ReferenceConfig<UserService> serviceConfigUserService(OrderServiceImpl orderServiceImpl,
                                                                 ApplicationConfig applicationConfig,  RegistryConfig registryConfig) {
        ReferenceConfig<UserService> config = new ReferenceConfig<>();
        config.setInterface(UserService.class);
        config.setId("userService");
        //设置Application
        config.setApplication(applicationConfig);
        //设置注册中心
        config.setRegistry(registryConfig);

        //给orderService注入调用接口
        orderServiceImpl.setUserService(config.get());
        return  config;
    }
}