package com.echoes.easyform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 劳威锟
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
//(value="SaUser对象", description="")
public class SaUser implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //Property(value = "用户名")
    private String username;

    //Property(value = "密码")
    private String password;

    //Property(value = "电话号码(大陆)")
    private String phone;

    //Property(value = "邮箱")
    private String email;

    //Property(value = "头像")
    private String avatar;

    //Property(value = "是否为管理员(0：否，1：是)")
    private Integer isAdmin;

    //Property(value = "创建时间")
    private Date createTime;

    //Property(value = "更新时间")
    private Date updateTime;

    //Property(value = "个性签名")
    private String signature;

    private String nickname;
}
