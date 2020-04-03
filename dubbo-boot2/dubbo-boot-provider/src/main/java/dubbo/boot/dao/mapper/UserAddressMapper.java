package dubbo.boot.dao.mapper;

import dubbo.boot.dao.entity.UserAddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 设置spring自动装配mapper到spring容器，或者在springboot启动了加上注解@MapperScan("dubbo.boot.dao.mapper")
 */
@Mapper
/*
 这是因为该注解的作用不只是将类识别为Bean，同时它还能将所标注的类中抛出的数据访问异常
 封装为 Spring 的数据访问异常类型。*/
@Repository
public interface UserAddressMapper {

    int  getCount();

    List<UserAddressEntity> getAddressList();

    int insert(UserAddressEntity address);

    List<UserAddressEntity> getAddressById(String userId);

}
