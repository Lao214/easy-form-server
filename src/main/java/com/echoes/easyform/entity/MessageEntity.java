package com.echoes.easyform.entity;/*
 *@title MessageEntity
 *@description
 *@author echoes
 *@version 1.0
 *@create 2025/2/10 15:40
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

    // 发送者的 id;
    private Long sender;
    // 接受者的 id
    private Long receiver;
    // 具体信息
    private String message;
    // 发送时间
    private Date time;
}
