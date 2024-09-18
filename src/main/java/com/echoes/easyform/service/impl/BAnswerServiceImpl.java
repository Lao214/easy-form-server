package com.echoes.easyform.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echoes.easyform.entity.BAnswer;
import com.echoes.easyform.mapper.BAnswerMapper;
import com.echoes.easyform.service.BAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-09-18
 */
@Service
public class BAnswerServiceImpl extends ServiceImpl<BAnswerMapper, BAnswer> implements BAnswerService {

    @Override
    public IPage<BAnswer> selectPage(Page<BAnswer> pageParam, BAnswer bAnswer) {
        return baseMapper.selectPage(pageParam, bAnswer);
    }
}
