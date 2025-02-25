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
 * 通讯消息，好友消息
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="BMessage对象", description="通讯消息，好友消息")
public class BMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@ApiModelProperty(value = "信息内容")
    private String msgContent;

    //@ApiModelProperty(value = "发送者")
    private Long sender;

    //@ApiModelProperty(value = "发送时间")
    private Date createTime;

    //@ApiModelProperty(value = "被阅读时间")
    private Date readTime;

    //@ApiModelProperty(value = "0 未读，1已读")
    private Integer isRead;

    private Long msgTo;


}
