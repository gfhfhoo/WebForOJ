package me.endnether.webforoj.service.impl;

import me.endnether.webforoj.entity.UserCopy;
import me.endnether.webforoj.mapper.RegistratorMapper;
import me.endnether.webforoj.service.RegistratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
    注册服务层，实现用户注册与数据库的通信
 */

@Service
public class RegistratorServiceImple implements RegistratorService {
    @Resource
    private RegistratorMapper registrator;

    @Override
    public int register(UserCopy user) {
        return registrator.register(user);
    }
}
