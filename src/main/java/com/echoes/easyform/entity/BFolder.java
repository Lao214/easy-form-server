package com.echoes.easyform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 劳威锟
 * @since 2025-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="BFolder对象", description="")
public class BFolder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@ApiModelProperty(value = "文件夹名称")
    private String name;

    //@ApiModelProperty(value = "所属用户")
    private String username;

    //@ApiModelProperty(value = "创建时间")
    private Date createTime;

    //@ApiModelProperty(value = "更新时间")
    private Date updateTime;

    //@ApiModelProperty(value = "已放置问卷数")
    private Integer formSum;

    //@ApiModelProperty(value = "是否公开(0：不公开，1: 公开)")
    private Integer isPublic;

    @TableField(exist = false)
    private List<String> formIds;

}
