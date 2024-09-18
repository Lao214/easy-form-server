package com.echoes.easyform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echoes.easyform.entity.BAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-09-18
 */
public interface BAnswerService extends IService<BAnswer> {

    IPage<BAnswer> selectPage(Page<BAnswer> pageParam, BAnswer bAnswer);
}
