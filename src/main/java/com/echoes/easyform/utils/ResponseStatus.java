package com.echoes.easyform.utils;

/*
 *@title ResponseStatus
 *@description
 *@author echoes
 *@version 1.0
 *@create 2024/6/26 16:44
 */

/**
 * 响应状态枚举
 */
public enum ResponseStatus {
    /**
     * 内置状态
     */
    OK(200,"操作成功"),
    INTERNAL_ERROR(500,"系统错误"),
    BUSINESS_ERROR(501,"业务错误"),
    TOKEN_NOT_EXIST(506,"token不存在"),
    TOKEN_VALID(507, "token鉴权失败！,请重新登录"),
    AUTH_FAILURE(508,"授权失败"),
    UN_ACCESS(509,"授权失败"),
    LOGIN_ERROR(502,"账号或密码错误"),
    TOKEN_IS_EXPIRED(510,"token过期,请重新登录"),
    NO_DATA_ERROR(503,"没有找到数据"),
    PARAM_ERROR(504,"参数格式错误"),
    UN_AUTH(401,"没有权限,需要登录");

    //响应代码
    private Integer code;
    //响应消息
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResponseStatus(Integer status, String message) {
        this.code = status;
        this.message = message;
    }
}
