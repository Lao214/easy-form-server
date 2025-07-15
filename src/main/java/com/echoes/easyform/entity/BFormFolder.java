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
 * 中间表：问卷 <-> 文件夹
 * </p>
 *
 * @author 劳威锟
 * @since 2025-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="BFormFolder对象", description="中间表：问卷 <-> 文件夹")
public class BFormFolder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@ApiModelProperty(value = "问卷ID")
    private Long formId;

    //@ApiModelProperty(value = "文件夹ID")
    private Long folderId;

    //@ApiModelProperty(value = "创建时间")
    private Date createTime;


}
