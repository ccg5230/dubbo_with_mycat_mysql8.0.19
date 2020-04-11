package dubbo.boot;

/**
 * @className ShardingTest
 * @Description
 * @Author chungaochen
 * Date 2020/4/8 15:45
 * Version 1.0
 **/

import dubbo.boot.dao.entity.User;
import dubbo.boot.dao.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//juit测试类所在包名需要和springboot启动类一样
public class ShardingTest {

    private static final Logger log = LoggerFactory.getLogger(ShardingTest.class);

    /* 可以直接测试mybatis,但是Sharding-jdbc还需要再学习 */
    @Autowired
    UserMapper userMapper;


    @Test
    @Rollback(false)
    public void addUser() {

        User u = new User();
        u.setId(5L);
        u.setMobile("13324567890");
        u.setPassword("afdaaewe23#$%");
        u.setCreateTime(new Date());
        u.setDelFlag("0");
        userMapper.insert(u);
    }


    @Test
    @Rollback(false)
    public void getUser() {

        User u = userMapper.getById(1L);
    }

}