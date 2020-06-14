package me.endnether.webforoj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.endnether.webforoj.entity.User;
import me.endnether.webforoj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
public class Util {
    private static String key;

    public static String getKey() {
        return key;
    }

    @Value("${onlinejudge.transaction.secretkey}")
    public void setKey(String key) {
        Util.key = key;
    }

    public static String toMD5(String s) {
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }

    public static String toMD5WithSalt(String s, String salt) {
        String res = s + salt;
        return DigestUtils.md5DigestAsHex(res.getBytes());
    }

    public static String encryptWithHmacSHA256(String secretKey, String data) {
        String finalString = null;
        StringBuilder builder = new StringBuilder();
        try {
            byte[] keys = secretKey.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keys, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            byte[] secretByte = mac.doFinal(data.getBytes());
            for (int i = 0; secretByte != null && i < secretByte.length; ++i) {
                finalString = Integer.toHexString(secretByte[i] & 0XFF);
                if (finalString.length() == 1) builder.append('0');
                builder.append(finalString);
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return builder.toString().toUpperCase();
    }

    public static Map<String, Object> getMap(String jsonString) {
        return JSONObject.parseObject(jsonString);
    }

    public static String getJsonString(Map<String, String> map) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(map);
        return formattedJsonString(jsonArray.toJSONString());
    }

    public static String getJsonString(List<Map<String, Object>> list) {
        return formattedJsonString(JSON.toJSONString(list));
    }

    public static String decryptToken(String token) {
        String[] arr = token.split(".");
        return new String(Base64.getDecoder().decode(arr[0].getBytes()));
    }

    public static String formattedJsonString(String s) {
        return s.substring(1, s.length() - 1);
    }
}
