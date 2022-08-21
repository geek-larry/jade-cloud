package com.jade.common.core.util;

import cn.hutool.core.convert.Convert;
import com.jade.common.core.constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class JwtUtil {

    public static String secret = CommonConstant.SECRET;

    public String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Boolean checkToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException jwtException) {
            return false;
        }
    }

    public String getUserKey(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, CommonConstant.USER_KEY);
    }

    public String getUserId(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, CommonConstant.DETAILS_USER_ID);
    }

    public String getUserName(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, CommonConstant.DETAILS_USERNAME);
    }

    public String getValue(Claims claims, String key) {
        return Convert.toStr(claims.get(key), "");
    }

}
