package com.echoes.easyform.config;/*
 *@title AppConfig
 *@description
 *@author echoes
 *@version 1.0
 *@create 2025/2/10 15:32
 */

import com.echoes.easyform.handler.ws.CustomSpringConfigurator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义的 Spring 上下文处理的配置，这里是为了防止 WebSocket 启用时无法正确的加载上下文
 * 简单展示了以上一些基本的配置后，再来介绍对数据的存储和处理部分，为了简便数据库的操作。
 */

@Configuration
@ConditionalOnWebApplication
public class AppConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    @Bean
    public CustomSpringConfigurator customSpringConfigurator() {
        return new CustomSpringConfigurator();
    }

}
