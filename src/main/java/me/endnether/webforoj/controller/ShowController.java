package me.endnether.webforoj.controller;

import me.endnether.webforoj.Util;
import me.endnether.webforoj.mapper.BoardCastInfoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class ShowController {
    @Resource
    private BoardCastInfoMapper boardCastInfoMapper;
    @Resource
    private Util util;

    @GetMapping("/Auth/Login")
    public String showLogin() {
        return "";
    }

    @GetMapping("/Auth/Register")
    public String showRegister() {
        return "";
    }

    @GetMapping("/api/Getannos")
    public String getAnnos() {
        List<Map<String, Object>> list = boardCastInfoMapper.getAllBoardCastInfo();
        return util.getJsonString(list);
    }
}
