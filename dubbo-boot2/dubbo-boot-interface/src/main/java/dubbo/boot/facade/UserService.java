package dubbo.boot.facade;

import dubbo.boot.domain.UserAddress;

import java.util.List;

/**
 * @className UserService
 * @Description
 * @Author chungaochen
 * Date 2020/3/14 18:55
 * Version 1.0
 **/
public interface UserService {

    /**
     * @Author chungaochen
     * @Description  查询所有用户地址
     * @Date 19:10 19:10
     * @Param [] 
     * @return java.util.List<com.dubbo.bean.UserAddress>
     **/
    List<UserAddress> queryAllUserAddress(String userId);

    boolean insert(UserAddress address);
}
