package com.echoes.easyform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Ignore;

/**
 * <p>
 * 
 * </p>
 *
 * @author 劳威锟
 * @since 2023-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
//(value="BForm对象", description="")
public class BForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //Property(value = "表单名称")
    private String formName;

    private String formKey;

    //Property(value = "创建者id")
    private Long userId;

    //Property(value = "状态(0：未发布，1：已发布)")
    private Integer formStatus;

    //Property(value = "创建时间")
    private Date createTime;

    //Property(value = "更新时间")
    private Date updateTime;

    //Property(value = "反馈逻辑")
    private String evaluateLogic;

    private String evaluateUi;

    //Property(value = "表单项")
    private String formItems;

    //Property(value = "表单类型(0：经典类型，1：卡片类型)")
    private Integer formType;

    //Property(value = "描述")
    private String description;

    //Property(value = "描述")
    private Long writeCount;

    @TableField(exist = false)
    private String sortType;

}
