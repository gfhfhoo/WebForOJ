package me.endnether.webforoj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @GetMapping("/user/{id}")
    public String getProfile(@PathVariable String id) {
        return "";
    }
}
