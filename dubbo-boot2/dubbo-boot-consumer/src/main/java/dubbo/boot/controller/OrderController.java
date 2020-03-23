package dubbo.boot.controller;

import dubbo.boot.domain.UserAddress;
import dubbo.boot.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className OrderController
 * @Description
 * @Author chungaochen
 * Date 2020/3/16 1:52
 * Version 1.0
 **/


@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "获取客户地址")
    @RequestMapping(value = "getAddress", method = RequestMethod.GET)
    public List<UserAddress> getAddress(@RequestParam(name = "userId",required = false)String userId) {
        return this.orderService.getAddress(userId);
    }

    @ApiOperation(value = "添加客户地址：实现增加了springcloud断路器Hystrix随机报错处理")
    @RequestMapping(value = "addAddress", method = RequestMethod.POST)
    public boolean addAddress(@RequestBody UserAddress address) {
        return this.orderService.insertAddress(address);
    }
}