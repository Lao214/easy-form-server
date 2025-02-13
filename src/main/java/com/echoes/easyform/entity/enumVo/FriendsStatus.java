package com.echoes.easyform.entity.enumVo;

/**
 * @title FriendsStatus
 * @description 定义好友关系的状态枚举类
 * @author echoes
 * @version 1.0
 * @create 2025/2/11 10:35
 */
public enum FriendsStatus {
    /**
     * 状态：待处理
     */
    PENDING("pending"),

    /**
     * 状态：已接受
     */
    ACCEPTED("accepted"),

    /**
     * 状态：已阻止
     */
    BLOCKED("blocked");

    // 状态值
    private final String status;

    // 构造函数
    FriendsStatus(String status) {
        this.status = status;
    }

    // 获取状态值
    public String getStatus() {
        return status;
    }

    // 根据字符串值获取枚举类型
    public static FriendsStatus fromString(String status) {
        for (FriendsStatus friendsStatus : FriendsStatus.values()) {
            if (friendsStatus.getStatus().equals(status)) {
                return friendsStatus;
            }
        }
        throw new IllegalArgumentException("Invalid FriendsStatus value: " + status);
    }
}