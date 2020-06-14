package me.endnether.webforoj.controller;

import me.endnether.webforoj.Util;
import me.endnether.webforoj.entity.User;
import me.endnether.webforoj.entity.UserCopy;
import me.endnether.webforoj.mapper.RegistratorMapper;
import me.endnether.webforoj.mapper.UserMapper;
import me.endnether.webforoj.service.UserService;
import me.endnether.webforoj.service.impl.RegistratorServiceImple;
import me.endnether.webforoj.service.impl.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static me.endnether.webforoj.enums.Status.STATUS_ERROR;
import static me.endnether.webforoj.enums.Status.STATUS_REGISTERED;

@RestController
public class RegisterController {
    @Autowired
    private RegistratorServiceImple registratorServiceImple;
    @Autowired
    private UserServiceImple userServiceImple;
    @Resource
    private UserMapper userMapper;

    @PostMapping("/Auth/RegisterVerify")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        if (userMapper.getExisted(username) != 0) return STATUS_REGISTERED.toJson();
        String encryptedPw = Util.toMD5WithSalt(password, username);//为密码加盐
        UserCopy usercopy = new UserCopy(username, encryptedPw);
        int line = registratorServiceImple.register(usercopy);//注册并自动注入userId到userCopy对象里
        if (line == 0) return STATUS_ERROR.toJson();
        Map<String, String> map = new HashMap<>();
        map.put("token", userServiceImple.generateToken(Integer.parseInt(usercopy.getUserId())));//生成token并服务器数据库、redis储存
        /*
            储存相关基本信息(注册时间，身份)
         */
        return Util.getJsonString(map);//返回给前台token
    }
}
