package me.endnether.webforoj;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings("all")
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void del(String... keys) {
        if (keys != null && keys.length >= 1) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else redisTemplate.delete(CollectionUtils.arrayToList(keys));
        }
    }

    public Object get(String key) {
        if (key == null) return null;
        else return redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object val) {
        try {
            redisTemplate.opsForValue().set(key, val);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, Object val, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, val, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, val);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
