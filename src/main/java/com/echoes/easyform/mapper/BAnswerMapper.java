package com.echoes.easyform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echoes.easyform.entity.BAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2023-09-18
 */
public interface BAnswerMapper extends BaseMapper<BAnswer> {

    IPage<BAnswer> selectPage(Page<BAnswer> pageParam, @Param("vo") BAnswer vo);

}
