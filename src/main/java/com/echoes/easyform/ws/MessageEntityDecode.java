package com.echoes.easyform.ws;/*
 *@title MessageEntityDecode
 *@description
 *@author echoes
 *@version 1.0
 *@create 2025/2/10 15:44
 */

import com.echoes.easyform.entity.MessageEntity;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * 关于该消息实体的编码和解码器
 */

@Component
public class MessageEntityDecode implements Decoder.Text<MessageEntity> {

    @Override
    public MessageEntity decode(String s) {
        // 利用 gson 处理消息实体，并格式化日期格式
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .fromJson(s, MessageEntity.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {}

    @Override
    public void destroy() {}

}

