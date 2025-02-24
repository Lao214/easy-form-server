package com.echoes.easyform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 好友关系表
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="BFriends对象", description="好友关系表")
public class BFriends implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@ApiModelProperty(value = "发起好友申请ID")
    private Long userId;

    //@ApiModelProperty(value = "被添加用户ID")
    private Long friendId;

    //@ApiModelProperty(value = "状态")
    private String status;

    //@ApiModelProperty(value = "创建时间")
    private Date createTime;

    //@ApiModelProperty(value = "用户设置的群组")
    private Long userGroupId;

    //@ApiModelProperty(value = "被添加人设置的群组")
    private Long friendGroupId;

    private String applyText;

    @TableField(exist = false)
    private String nickname;

    @TableField(exist = false)
    private String avatar;
}
