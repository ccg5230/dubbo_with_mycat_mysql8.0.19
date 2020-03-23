package dubbo.boot.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dubbo.boot.domain.UserAddress;
import dubbo.boot.facade.UserService;
import dubbo.boot.service.OrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @className OrderServiceImpl
 * @Description
 * @Author chungaochen
 * Date 2020/3/14 19:46
 * Version 1.0
 **/
@Service //使用spring注解由Ioc生成bean，都表明这些类是要交给spring容器管理
public class OrderServiceImpl implements OrderService {

    /*dubbo默认重试2次共调用3次，对于非幂等性操作要注意设置重试次数
    * 使用@Reference引用服务,注释掉可以改用dubboconfig实现--用springboot配置注入方式，
     */
    @Reference(retries = 0)
    private UserService userService;

    /** dubboconfig实现需要增加set注入方法*/
    public void setUserService() {
        setUserService();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @HystrixCommand(fallbackMethod = "errorFallback")
    @Override
    public List<UserAddress> getAddress(String userId) {
        return userService.queryAllUserAddress(userId);
    }

    @Override
    public Boolean insertAddress(UserAddress address) {
        return userService.insert(address);
    }

    public List<UserAddress> errorFallback(String userId) {
        return Arrays.asList(new UserAddress("服务端错误Hystrix处理","Hystrix fallbackMethod ","error"));
    }
}