package com.echoes.easyform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 好友分组表
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="BFriendsGroups对象", description="好友分组表")
public class BFriendsGroups implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@ApiModelProperty(value = "用户ID")
    private Long userId;

    //@ApiModelProperty(value = "分组名称")
    private String name;

    //@ApiModelProperty(value = "创建时间")
    private Date createTime;


}
