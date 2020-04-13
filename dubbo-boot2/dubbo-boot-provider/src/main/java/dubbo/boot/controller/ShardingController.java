package dubbo.boot.controller;

import dubbo.boot.dao.entity.User;
import dubbo.boot.dao.entity.UserAddressEntity;
import dubbo.boot.dao.mapper.UserAddressMapper;
import dubbo.boot.domain.UserAddress;
import dubbo.boot.dynamicdataSource.annotation.TargetDataSource;
import dubbo.boot.dynamicdataSource.common.DatabaseType;
import dubbo.boot.service.ShardingUserService;
import dubbo.boot.swagger.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className ShardingController
 * @Description
 * @Author chungaochen
 * Date 2020/4/8 21:17
 * Version 1.0
 **/
@RestController
@RequestMapping("/sharding")
public class ShardingController {
    private static final Logger logger = LoggerFactory.getLogger(ShardingController.class);

    @Autowired
    private ShardingUserService userService;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "添加客户")
    @TargetDataSource(source = DatabaseType.SHARDING_DATA_SOURCE)
    public boolean addUser(@RequestBody User user) {
        return userService.save(user) > 0;
    }

    /**
     * swagger 直接返回实体类，long需要转为json，否则丢失精度。可以手动转string,也可以在configuration配置
     */
    @RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "查找客户")
    //swagger2中long类型swagger-ui页面显示integer
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType="path", dataType = "Long")
    @TargetDataSource(source = DatabaseType.SHARDING_DATA_SOURCE)
    public JsonResult<User> getUser(@PathVariable("userId") Long userId) {
        return new JsonResult<User>().ok(userService.getById(userId));
    }

    @ApiOperation(value = "获取客户地址,使用springboot动态数据源，注解方式")
    @RequestMapping(value = "getAddress/mycat", method = RequestMethod.GET)
    @TargetDataSource(source = DatabaseType.MYCAT_DATA_SOURCE)
    public JsonResult<List<UserAddress>> getAddressLinkMycat(@RequestParam(name = "userId", required = false) String userId) {
        List<UserAddressEntity> list;
        if (!StringUtils.isEmpty(userId)) {
            list = userAddressMapper.getAddressById(userId);
        } else {
            list = userAddressMapper.getAddressList();
        }

        List<UserAddress> results = convertUserAddresses(list);
        return new JsonResult<List<UserAddress>>().ok(results);
    }

    @ApiOperation(value = "获取客户地址,使用springboot动态数据源，注解方式")
    @RequestMapping(value = "getAddress/mysql", method = RequestMethod.GET)
    @TargetDataSource(source = DatabaseType.MYSQL_DATA_SOURCE)
    public JsonResult<List<UserAddress>> getAddressLinkMysql(@RequestParam(name = "userId", required = false) String userId) {
        List<UserAddressEntity> list;
        if (!StringUtils.isEmpty(userId)) {
            list = userAddressMapper.getAddressById(userId);
        } else {
            list = userAddressMapper.getAddressList();
        }

        List<UserAddress> results = convertUserAddresses(list);
        return new JsonResult<List<UserAddress>>().ok(results);
    }


    private List<UserAddress> convertUserAddresses(List<UserAddressEntity> list) {
        return list.stream().map(a -> {
            UserAddress address = new UserAddress();
            BeanUtils.copyProperties(a, address);
            return address;
        }).collect(Collectors.toList());
    }
}