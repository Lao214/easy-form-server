package com.echoes.easyform.handler.ws;/*
 *@title WebSocketSingleServer
 *@description
 *@author echoes
 *@version 1.0
 *@create 2025/2/10 14:34
 */

import com.echoes.easyform.entity.BMessage;
import com.echoes.easyform.entity.MessageEntity;
import com.echoes.easyform.service.BMessageService;
import com.echoes.easyform.utils.RedisUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * websocket 服务器的配置
 */

@Component
// 配置 websocket 的路径
@ServerEndpoint(
        value = "/websocket/{id}",
        decoders = { MessageEntityDecode.class },
        encoders = { MessageEntityEncode.class },
        configurator = CustomSpringConfigurator.class
)
@Slf4j
public class WebSocketServer {

    private Session session;
    private final Gson gson;
    private final RedisUtil redis;
    // 存储所有的用户连接
    private static final Map<Long, Session> WEBSOCKET_MAP = new ConcurrentHashMap<>();

    @Autowired
    private BMessageService bMessageService;

    @Autowired
    public WebSocketServer(Gson gson, RedisUtil redis) {
        this.gson = gson;
        this.redis = redis;
    }

    @OnOpen
    public void onOpen(@PathParam("id") Long id, Session session) {
        this.session = session;
        // 根据 /websocket/{id} 中传入的用户 id 作为键，存储每个用户的 session
        WEBSOCKET_MAP.put(id, session);
    }

    @OnMessage
    public void onMessage(MessageEntity message) {
        // 生成 Redis 键
        String key = LongStream.of(message.getSender(), message.getReceiver())
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("-"));

        try {
            // 1. 先写入 MySQL
            BMessage bMessage = new BMessage();
            bMessage.setMsgContent(message.getMessage());
            bMessage.setSender(message.getSender());
            bMessage.setCreateTime(new Date());
            bMessage.setMsgTo(message.getReceiver());
            bMessageService.saveMessage(bMessage);

            // 2. 再写入 Redis
            redis.setMsg(key, message);

            // 3. 异步发送 WebSocket 消息
            if (WEBSOCKET_MAP.get(message.getReceiver()) != null) {
                Session session = WEBSOCKET_MAP.get(message.getReceiver());
                session.getAsyncRemote().sendText(gson.toJson(message), new SendHandler() {
                    @Override
                    public void onResult(SendResult result) {
                        if (!result.isOK()) {
                            log.error("Failed to send WebSocket message to receiver: {}", message.getReceiver());
                        }
                    }
                });
            }

        } catch (Exception e) {
            // 如果 MySQL 或 Redis 操作失败，回滚 Redis 数据
            redis.deleteLatestMsg(key);
            log.error("Failed to process message, rolled back Redis operation", e);
            throw new RuntimeException("Failed to process message", e); // 触发事务回滚
        }
    }

    @OnClose
    public void onClose() {
        // 用户退出时，从 map 中删除信息
        for (Map.Entry<Long, Session> entry : WEBSOCKET_MAP.entrySet()) {
            if (this.session.getId().equals(entry.getValue().getId())) {
                WEBSOCKET_MAP.remove(entry.getKey());
                return;
            }
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

}


