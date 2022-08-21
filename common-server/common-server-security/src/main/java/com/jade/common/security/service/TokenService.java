package com.jade.common.security.service;

import cn.hutool.core.lang.UUID;
import com.jade.common.core.constant.CommonConstant;
import com.jade.common.core.util.IpUtil;
import com.jade.common.core.util.JwtUtil;
import com.jade.common.core.util.ServletUtil;
import com.jade.common.core.util.TokenUtil;
import com.jade.common.redis.util.RedisUtil;
import com.jade.common.security.constant.SecurityConstant;
import com.jade.common.security.domain.LoginUserDetail;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TokenService {

    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private final static long expireTime = SecurityConstant.EXPIRATION;
    private final static String ACCESS_TOKEN = SecurityConstant.LOGIN_TOKEN_KEY;
    private final static Long MILLIS_MINUTE_TEN = SecurityConstant.REFRESH_TIME * MILLIS_MINUTE;
    private final RedisUtil redisUtil;

    public Map<String, Object> createToken(LoginUserDetail loginUser) {
        String token = UUID.fastUUID().toString();
        loginUser.setToken(token);
        loginUser.setLoginIp(IpUtil.getIpAddr(ServletUtil.getRequest()));
        refreshToken(loginUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(CommonConstant.USER_KEY, token);
        claimsMap.put(CommonConstant.DETAILS_USER_ID, loginUser.getUserId());
        claimsMap.put(CommonConstant.DETAILS_USERNAME, loginUser.getUsername());

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put("access_token", JwtUtil.createToken(claimsMap));
        rspMap.put("expires_in", expireTime);
        return rspMap;
    }

    public LoginUserDetail getLoginUser() {
        return getLoginUser(ServletUtil.getRequest());
    }

    public void setLoginUser(LoginUserDetail loginUser) {
        if (!Objects.isNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    public LoginUserDetail getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = TokenUtil.getToken(request);
        return getLoginUser(token);
    }

    public LoginUserDetail getLoginUser(String token) {
        LoginUserDetail user = null;
        try {
            if (StringUtils.isNotEmpty(token)) {
                String userkey = JwtUtil.getUserKey(token);
                user = redisUtil.getCacheObject(getTokenKey(userkey));
                return user;
            }
        } catch (Exception ignored) {
        }
        return user;
    }

    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userkey = JwtUtil.getUserKey(token);
            redisUtil.deleteObject(getTokenKey(userkey));
        }
    }

    public void verifyToken(LoginUserDetail loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    public void refreshToken(LoginUserDetail loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisUtil.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    public String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }
}