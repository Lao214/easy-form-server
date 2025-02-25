package com.echoes.easyform.controller;


import com.echoes.easyform.service.BMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 通讯消息，好友消息 前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-24
 */
@RestController
@RequestMapping("/easy/message")
public class BMessageController {

    @Autowired
    private BMessageService bMessageService;


}

