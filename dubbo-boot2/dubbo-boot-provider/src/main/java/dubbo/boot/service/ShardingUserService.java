package dubbo.boot.service;

import dubbo.boot.dao.entity.User;

/**
 * @className ShardingUserService
 * @Description
 * @Author chungaochen
 * Date 2020/4/8 17:46
 * Version 1.0
 **/
public interface ShardingUserService {

    User getById(Long id);

    int update(User user);

    int save(User user);
}