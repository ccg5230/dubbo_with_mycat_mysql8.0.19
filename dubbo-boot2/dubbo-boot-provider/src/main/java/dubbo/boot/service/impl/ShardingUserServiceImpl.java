package dubbo.boot.service.impl;

import dubbo.boot.dao.entity.User;
import dubbo.boot.dao.mapper.UserMapper;
import dubbo.boot.service.ShardingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @className ShardingUserServiceImpl
 * @Description
 * @Author chungaochen
 * Date 2020/4/8 17:48
 * Version 1.0
 **/
@Service
public class ShardingUserServiceImpl implements ShardingUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public int update(User user) {
        user.setUpdateTime(new Date());
        return userMapper.update(user);
    }

    @Override    public int save(User user) {
        user.setCreateTime(new Date());
        return userMapper.insert(user);
    }

}