package me.endnether.webforoj.service;

import me.endnether.webforoj.entity.User;

public interface UserService {

    public String getUsername(String userId);

    public String getPassword(String userId);

    public String getToken(String userId);

    public String generateToken(int userId);

    public String comparator(String s, int userId);

    public User ConstructUserByToken(String token);

    public String verifyUser(String userId, String token);
}
