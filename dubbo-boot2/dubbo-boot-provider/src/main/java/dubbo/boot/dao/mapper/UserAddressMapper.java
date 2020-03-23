package dubbo.boot.dao.mapper;

import dubbo.boot.dao.entity.UserAddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/*
 * @Description
    <P>
    设置spring自动装配mapper到spring容器，或者在springboot启动了加上注解@MapperScan("dubbo.boot.dao.mapper")
    </p>
 */
@Mapper
public interface UserAddressMapper {

    int  getCount();

    List<UserAddressEntity> getAddressList();

    int insert(UserAddressEntity address);

    List<UserAddressEntity> getAddressById(String userId);

}
