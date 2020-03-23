package dubbo.boot.cofig;

import dubbo.boot.facade.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className DubboConfig
 * @Description
 * @Author chungaochen
 * Date 2020/3/16 17:02
 * Version 1.0
 **/
//@Configuration
public class DubboConfig {

    /** 设置应用名称 **/
    @Bean
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig("dubbo-boot-provider");
    }

    /**  申明注册中心 **/
    @Bean
    public RegistryConfig registryConfig() {
        return new RegistryConfig("zookeeper://192.168.1.115:2181?backup=192.168.1.113:2181,192.168.1.114:2181");
    }

    /** 申明协议与端口 **/
    @Bean
    public ProtocolConfig protocolConfig() {
        return new ProtocolConfig("dubbo",20880);
    }

    /** 暴露服务 */
    @Bean
    public ServiceConfig<UserService> serviceConfigUserService(UserService userService,
                                                               ApplicationConfig applicationConfig, RegistryConfig registryConfig) {
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setWeight(800);//服务集群内部权重设置，默认为100

        //设置Application
        serviceConfig.setApplication(applicationConfig);
        //设置注册中心
        serviceConfig.setRegistry(registryConfig);
        //暴露方法
        serviceConfig.export();
        return  serviceConfig;
    }
}