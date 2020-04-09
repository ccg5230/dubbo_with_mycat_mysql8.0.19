package dubbo.boot.dao.mapper;

/**
 * @className UserMapper
 * @Description
 * @Author chungaochen
 * Date 2020/4/8 15:11
 * Version 1.0
 **/
import dubbo.boot.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
/*
 这是因为该注解的作用不只是将类识别为Bean，同时它还能将所标注的类中抛出的数据访问异常
 封装为 Spring 的数据访问异常类型。*/
@Repository
public interface UserMapper {

    User getById(Long id);

    int insert(User user);

    int update(User user);

}