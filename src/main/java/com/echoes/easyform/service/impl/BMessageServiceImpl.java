package com.echoes.easyform.service.impl;

import com.echoes.easyform.entity.BMessage;
import com.echoes.easyform.mapper.BMessageMapper;
import com.echoes.easyform.service.BMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 通讯消息，好友消息 服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-24
 */
@Service
public class BMessageServiceImpl extends ServiceImpl<BMessageMapper, BMessage> implements BMessageService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveMessage(BMessage bMessage) {
        return baseMapper.insert(bMessage) > 0;
    }
}
