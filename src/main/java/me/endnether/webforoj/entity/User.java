package me.endnether.webforoj.entity;

public class User extends UserBase {
    private String rank;

    public User(String userId) {
        super.setUserId(userId);
    }
}
