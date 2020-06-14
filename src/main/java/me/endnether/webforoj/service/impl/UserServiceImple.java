package me.endnether.webforoj.service.impl;

import com.alibaba.fastjson.JSON;
import me.endnether.webforoj.RedisUtil;
import me.endnether.webforoj.Util;
import me.endnether.webforoj.entity.User;
import me.endnether.webforoj.mapper.UserMapper;
import me.endnether.webforoj.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static me.endnether.webforoj.enums.Status.*;
import static me.endnether.webforoj.enums.Status.STATUS_TOKENUNEQUAL;

/*
    用户实体服务层，用于用户最基本的业务
 */

@Service
public class UserServiceImple implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public String getUsername(String userId) {
        return userMapper.getUsername(Integer.parseInt(userId));
    }

    @Override
    public String getPassword(String userId) {
        return userMapper.getPassword(Integer.parseInt(userId));
    }

    @Override
    @SuppressWarnings("all")
    public String getToken(String userId) {
        //查缓存
        if (redisUtil.hasKey(userId)) {
            Map<String, Object> map = (Map<String, Object>) redisUtil.get(userId);
            return (String) map.get("token");
        }
        //缓存查不到
        return userMapper.getToken(Integer.parseInt(userId));
    }

    @Override
    public String generateToken(int userId) {
        String res;
        String password = getPassword(String.valueOf(userId));
        if (userId == 0) return "";
        //生成时间戳和到期时间戳
        String stamp = String.valueOf(System.currentTimeMillis() / 1000);
        String expireStamp = String.valueOf(System.currentTimeMillis() / 1000 + 259200L);//给定三天有效期
        //加密后密文（用户id和密码(md5)、时间戳）
        Map<String, String> map = new HashMap<>();
        map.put("userid", String.valueOf(userId));
        map.put("password", password);
        map.put("stamp", stamp);
        map.put("expire", expireStamp);
        String payload = Util.getJsonString(map);//使用fastJson储存用户id和密码、时间戳
        String encryptedPayload = Base64.getEncoder().encodeToString(payload.getBytes());//加密json格式的payload
        //利用加密后的payload生成签名
        String sign = Util.encryptWithHmacSHA256(Util.getKey(), encryptedPayload);
        //生成结果
        res = encryptedPayload + "." + sign;
        //返回加密后的token并储存到redis和数据库上，先判断是否存在该用户的token
        if (userMapper.getToken(userId) == null) {
            //设置各类信息
            userMapper.setAll(userId, res, stamp, expireStamp);
        } else {
            //更新时间戳
            userMapper.setNewStamp(userId, stamp);
            userMapper.setNewExpireStamp(userId, expireStamp);
            userMapper.setToken(userId, res);
        }
        //存放到redis
        if (redisUtil.hasKey(String.valueOf(userId))) redisUtil.del(String.valueOf(userId));//存在该用户token缓存就删掉，重新填写
        Map<String, String> redisMap = new HashMap<>();
        redisMap.put("token", res);
        redisMap.put("stamp", stamp);
        redisMap.put("expire", expireStamp);
        redisUtil.set(String.valueOf(userId), redisMap);
        return res;
    }

    @Override
    public String comparator(String s, int userId) {
        String[] arr = s.split("\\.");
        if (arr.length != 2) return STATUS_TOKENUNEQUAL.name();
        String waitToVerifyToken = arr[0];
        String sign = arr[1];
        String decryptedPayload = new String(Base64.getDecoder().decode(waitToVerifyToken.getBytes()));
        if (Util.encryptWithHmacSHA256(Util.getKey(), waitToVerifyToken).equals(sign)) {//比对前端发来的token签名后是否一致
            //检查时间戳是否过期
            long nowStamp = System.currentTimeMillis() / 1000;
            Map<String, Object> map = Util.getMap(decryptedPayload);
            String expireStamp = (String) map.get("expire");
            if (nowStamp - Long.parseLong(expireStamp) > 0) return STATUS_EXPIRED.name();//过期
            else return STATUS_NOTEXPIRED.name();
        } else return STATUS_TOKENUNEQUAL.name();
    }

    /*
        通过用户token构建一个用户实体类
     */

    @Override
    public User ConstructUserByToken(String token) {
        String decryptedToken = Util.decryptToken(token);
        Map<String, Object> map = JSON.parseObject(decryptedToken);
        String userId = (String) map.get("userid");
        return new User(userId);
    }

        /*
        验证用户的合法性，通过token比较器来返回结果
     */

    @Override
    public String verifyUser(String userId, String token) {
        User user = new User(userId);
        return this.comparator(token, Integer.parseInt(userId));
    }
}
