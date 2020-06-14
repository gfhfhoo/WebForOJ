package me.endnether.webforoj.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    String getPassword(int id);

    String getUsername(int id);

    String getToken(int id);

    void setToken(@Param("id") int id, @Param("token") String token);

    void setNewStamp(@Param("id") int id, @Param("stamp") String stamp);

    void setNewExpireStamp(@Param("id") int id, @Param("stamp") String stamp);

    int getExisted(String username);

    int setAll(@Param("id") int id, @Param("token") String token, @Param("stamp") String stamp, @Param("expire") String expire);
}
