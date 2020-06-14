package me.endnether.webforoj.enums;

import com.alibaba.fastjson.JSONArray;
import me.endnether.webforoj.Util;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public enum Status {
    STATUS_OK("OK"),
    STATUS_ERROR("ERROR"),
    STATUS_EXPIRED("EXPIRED"),
    STATUS_NOTEXPIRED("NOTEXPIRED"),
    STATUS_TOKENUNEQUAL("TOKENUNEQUAL"),
    STATUS_SUBMITTED("SUBMITTED"),
    STATUS_REGISTERED("REGISTERED");

    private final String status;
    @Resource
    private Util util;

    private Status(String status) {
        this.status = status;
    }

    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("status", this.name());
        return util.getJsonString(map);
    }
}
