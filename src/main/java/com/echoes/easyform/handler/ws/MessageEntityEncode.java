package com.echoes.easyform.handler.ws;/*
 *@title MessageEntityEncode
 *@description
 *@author echoes
 *@version 1.0
 *@create 2025/2/10 15:57
 */

import com.echoes.easyform.entity.MessageEntity;
import com.google.gson.GsonBuilder;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEntityEncode implements Encoder.Text<MessageEntity> {

    @Override
    public String encode(MessageEntity messageEntity) {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .toJson(messageEntity);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {}

    @Override
    public void destroy() {}

}

