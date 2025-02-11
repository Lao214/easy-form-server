package com.echoes.easyform.config;/*
 *@title WebSocketConfig
 *@description
 *@author echoes
 *@version 1.0
 *@create 2025/2/10 14:01
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig {
    /**
     * 注入一个ServerEndpointExporter，该Bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
