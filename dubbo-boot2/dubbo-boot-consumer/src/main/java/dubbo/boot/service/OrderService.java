package dubbo.boot.service;

import dubbo.boot.domain.UserAddress;
import java.util.List;

/**
 * @className OrderService
 * @Description
 * @Author chungaochen
 * Date 2020/3/14 19:41
 * Version 1.0
 **/
public interface OrderService {

    List<UserAddress> getAddress(String userId);

    Boolean insertAddress(UserAddress address);
}
