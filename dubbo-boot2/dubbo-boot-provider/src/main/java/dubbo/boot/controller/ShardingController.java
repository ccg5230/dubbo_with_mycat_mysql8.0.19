package dubbo.boot.controller;

import dubbo.boot.dao.entity.User;
import dubbo.boot.dao.mapper.UserAddressMapper;
import dubbo.boot.service.ShardingUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ShardingUserService userService;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "添加客户")
    public boolean addUser(@RequestBody User user) {
        return userService.save(user) > 0;
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查找客户")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

}