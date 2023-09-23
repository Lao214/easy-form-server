package com.echoes.easyform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
//(value="BAnswer对象", description="")
public class BAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //Property(value = "表单key")
    private String formKey;

    //Property(value = "回答的详情")
    private String answerDetails;

    //Property(value = "创建时间")
    private Date createTime;

    //Property(value = "渠道")
    private String source;

    //Property(value = "提交IP")
    private String commitIp;

    //Property(value = "完成时间")
    private Long completeTime;

    @TableField(exist = false)
    private String logic;

}
