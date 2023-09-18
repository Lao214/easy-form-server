package com.echoes.easyform.mapper;

import com.echoes.easyform.entity.BForm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2023-09-09
 */
public interface BFormMapper extends BaseMapper<BForm> {

    boolean updateByKey(BForm form);
}
