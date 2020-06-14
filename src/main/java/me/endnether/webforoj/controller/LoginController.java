package me.endnether.webforoj.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/Auth/LoginVerify")
    public String login(@RequestParam("cookie") String cookie) {
        return "";
    }
}