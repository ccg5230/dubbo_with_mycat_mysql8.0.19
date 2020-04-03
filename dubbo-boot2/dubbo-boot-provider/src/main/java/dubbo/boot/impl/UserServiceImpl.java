package dubbo.boot.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dubbo.boot.dao.entity.UserAddressEntity;
import dubbo.boot.dao.mapper.UserAddressMapper;
import dubbo.boot.domain.UserAddress;
import dubbo.boot.facade.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @className UserServiceImpl
 * @Description
 * @Author chungaochen
 * Date 2020/3/14 19:10
 * Version 1.0
 **/
/**
 *  声明该类是服务的提供者的注解,对外暴露服务
 *  配置权重和负载均衡算法：weight = 400,与"roundrobin"加权轮询负载均衡配合使用
 *  consistenthash 一致性hash算法，具体参考官网
 *  http://dubbo.apache.org/zh-cn/docs/source_code_guide/loadbalance.html 章节。
 * */
//@org.apache.dubbo.config.annotation.Service(weight = 400, loadbalance = "roundrobin")
@org.apache.dubbo.config.annotation.Service(loadbalance = "consistenthash")
/** 使用springboot configuration方式设置dubbo服务spring,需要注入bean到spring容器 */
//@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    private static  final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
//    @Resource
    private UserAddressMapper userAddressMapper;

    @Override
    @HystrixCommand
    public List<UserAddress> queryAllUserAddress(String userId) {
        if(Math.random() > 0.9){
            throw new RuntimeException("模拟服务报错");
        }
        List<UserAddressEntity> list;
        if(!StringUtils.isEmpty(userId)) {
            list =  userAddressMapper.getAddressById(userId);
        } else {
            list =  userAddressMapper.getAddressList();
        }

        return list.stream().map(a->{
            UserAddress address = new UserAddress();
            BeanUtils.copyProperties(a, address);
            return address;
        }).collect(Collectors.toList());
    }

    public boolean insert(UserAddress address) {
        UserAddressEntity entity = new UserAddressEntity();
        BeanUtils.copyProperties(address, entity);
        long timeMillis = new Date().getTime();
        entity.setCreateTime(new Timestamp(timeMillis));
        entity.setUpdateTime(new Timestamp(timeMillis));
        return userAddressMapper.insert(entity) > 0;
//        return  true;
    }

}