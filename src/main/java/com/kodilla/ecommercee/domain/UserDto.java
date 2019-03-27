package com.kodilla.ecommercee.domain;

public class UserDto {
    private Long userId;
    private String userName;
    private String isBlocked;
    private Long userIdKey;

    public UserDto() {
    }

    public UserDto(Long userId, String userName, String isBlocked, Long userIdKey) {
        this.userId = userId;
        this.userName = userName;
        this.isBlocked = isBlocked;
        this.userIdKey = userIdKey;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getIsBlocked() {
        return isBlocked;
    }

    public Long getUserIdKey() {
        return userIdKey;
    }
}