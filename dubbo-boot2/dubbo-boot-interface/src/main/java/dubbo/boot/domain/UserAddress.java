package dubbo.boot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className UserAdress
 * @Description
 * @Author chungaochen
 * Date 2020/3/14 18:56
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress implements Serializable //远程代用传递对象需要实现序列化
{

    public static final long serialVersionUID = 1L;

    private String userAddress;

    private String userName;

    private String userId;

    @Override
    public String toString() {
        return "UserAddress{" +
                ", userAddress='" + userAddress + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}