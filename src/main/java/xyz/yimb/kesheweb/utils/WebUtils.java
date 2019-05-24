package xyz.yimb.kesheweb.utils;

import org.springframework.util.DigestUtils;

public class WebUtils {
    public static String getMD5(String password){
        String digest = DigestUtils.md5DigestAsHex(password.getBytes());
        return digest;
    }
}
