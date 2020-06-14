package me.endnether.webforoj.entity;

import me.endnether.webforoj.RedisUtil;
import me.endnether.webforoj.mapper.UserMapper;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;


public class UserBase implements Serializable {
    private String username;
    private String token;
    private String userId;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
