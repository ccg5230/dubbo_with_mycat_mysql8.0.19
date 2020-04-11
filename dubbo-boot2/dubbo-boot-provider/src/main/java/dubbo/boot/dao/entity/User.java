package dubbo.boot.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @className UserInfo
 * @Description
 * @Author chungaochen
 * Date 2020/4/8 15:09
 * Version 1.0
 **/
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -2359336982098859444L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建日期
     */
    /** 不能加入 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 注解否则插入数据库报错，
     * Incorrect datetime value: '' for column '' at row 1*/
    private Date createTime;
    /**
     * 修改日期
     */
    private Date updateTime;
    /**
     * 删除标记 1:删除;0:未删除
     */
    private String delFlag;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}