package com.echoes.easyform.service.impl;

import com.echoes.easyform.entity.BForm;
import com.echoes.easyform.mapper.BFormMapper;
import com.echoes.easyform.service.BFormService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-09-09
 */
@Service
public class BFormServiceImpl extends ServiceImpl<BFormMapper, BForm> implements BFormService {

    @Override
    public boolean updateByKey(BForm form) {
        return baseMapper.updateByKey(form);
    }
}
