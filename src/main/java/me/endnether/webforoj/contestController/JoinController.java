package me.endnether.webforoj.contestController;

import me.endnether.webforoj.Util;
import me.endnether.webforoj.entity.User;
import me.endnether.webforoj.mapper.UserMapper;
import me.endnether.webforoj.service.impl.UserServiceImple;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class JoinController {
    @Resource
    private UserServiceImple userServiceImple;

    @RequestMapping("/api/contest/join")
    public String join(@RequestParam("token") String token,
                       @RequestParam("contestId") String contestId,
                       @RequestParam("ver") String verify) {
        User user = userServiceImple.ConstructUserByToken(token);//获取用户实例
        return "";
    }
}
