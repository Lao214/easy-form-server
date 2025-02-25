package com.echoes.easyform.service;

import com.echoes.easyform.entity.BMessage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 通讯消息，好友消息 服务类
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-24
 */
public interface BMessageService extends IService<BMessage> {

    boolean saveMessage(BMessage bMessage);
}
