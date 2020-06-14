package me.endnether.webforoj.controller;

import me.endnether.webforoj.Util;
import me.endnether.webforoj.queue.Producer;
import me.endnether.webforoj.service.impl.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static me.endnether.webforoj.enums.Status.*;

@RestController
public class SubmitController {
    @Autowired
    private UserServiceImple userServiceImple;

    /*
        mainly getting code and account's info by token
     */
    @PostMapping("/api/submit/{id}")
    public String getCore(@RequestParam("code") String code,
                          @RequestParam("token") String token,
                          @RequestParam("u") String userId,
                          @PathVariable String id) {
        String statusCode = userServiceImple.verifyUser(userId, token);//检验用户的token
        Map<String, String> map = new HashMap<>();
        if (statusCode.equals(STATUS_EXPIRED.name()) || statusCode.equals(STATUS_TOKENUNEQUAL.name())) {//过期了或者是token不正确
            map.put("status", "expired");
            return Util.getJsonString(map);
        } else {
            map.put("code", code);
            map.put("userid", userId);
            map.put("problemid", id);
            Producer.sendCores(Util.getJsonString(map));
            return STATUS_SUBMITTED.toJson();
        }
    }
}
