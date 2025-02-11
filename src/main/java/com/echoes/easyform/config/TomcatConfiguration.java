package com.echoes.easyform.config;/*
 *@title TomcatConfiguration
 *@description
 *@author echoes
 *@version 1.0
 *@create 2025/2/10 15:12
 */

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 为了使用 wss 协议而进行的 Tomcat 服务器配置，以便可以使用 https 协议：
 */

@Configuration
public class TomcatConfiguration {

    /**
     * 作用：创建一个 TomcatServletWebServerFactory Bean，它是 Spring Boot 用来配置嵌入式 Tomcat 服务器的工厂类。
     * 关键点：
     * TomcatServletWebServerFactory 用于配置 Tomcat 服务器。
     * tomcat.addAdditionalTomcatConnectors(createSslConnector());
     * 这个方法向 Tomcat 添加额外的 HTTP 连接器，即 createSslConnector() 方法返回的 Connector。
     * @return
     */

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        return tomcat;
    }

    /**
     * 作用：创建一个额外的 HTTP 连接器，并配置其端口和行为。
     * 关键点：
     * Connector 是 Tomcat 的连接器，使用 org.apache.coyote.http11.Http11NioProtocol 作为协议。
     * connector.setScheme("http");：声明该连接器使用 HTTP。
     * connector.setPort(8888);：设置此连接器监听的端口是 8888。
     * connector.setSecure(false);：表示这个连接器不使用 HTTPS。
     * connector.setRedirectPort(443);：如果客户端访问了 http://localhost:8888，Tomcat 会自动重定向到 https://localhost:443（即 HTTPS 端口）。
     * @return
     */

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8888);
        connector.setSecure(false);
        connector.setRedirectPort(443);
        return connector;
    }

    /**
     * 作用：用于在 Tomcat 服务器的 context 上注册 WebSocket 支持。
     * 关键点：
     * TomcatContextCustomizer 是一个 Spring Boot 提供的接口，可以用于修改 Tomcat 的 Context 配置。
     * context.addServletContainerInitializer(new WsSci(), null);
     * WsSci 是 Tomcat 提供的 WebSocket 初始化器，它用于启用 WebSocket 支持。
     * ⚠ 这个配置的作用是确保 Tomcat 能够支持 WebSocket。
     * @return
     */

    @Bean
    public TomcatContextCustomizer tomcatContextCustomizer() {
        return context -> context.addServletContainerInitializer(new WsSci(), null);
    }


    /**
     * 这个 TomcatConfiguration 主要完成两个任务：
     *
     * 1.额外的 HTTP 连接器
     * 让 Tomcat 监听 8888 端口的 HTTP 请求，并将其重定向到 443（HTTPS）。
     * 但并未真正开启 HTTPS（如果需要 HTTPS，还需要在 application.properties 里配置 SSL 证书）。
     * 2.WebSocket 支持
     * 通过 WsSci 让 Tomcat 服务器支持 WebSocket 连接。
     */

}

