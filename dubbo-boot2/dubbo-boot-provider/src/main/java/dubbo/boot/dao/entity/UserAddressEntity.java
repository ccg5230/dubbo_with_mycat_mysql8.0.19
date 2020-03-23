package dubbo.boot.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @className UserAddressEntity
 * @Description
 * @Author chungaochen
 * Date 2020/3/22 16:25
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressEntity implements Serializable {
    public static final long serialVersionUID = 1L;

    private long id;

    private String userAddress;

    private String userName;

    private String userId;

    private Timestamp createTime;

    private Timestamp updateTime;

}