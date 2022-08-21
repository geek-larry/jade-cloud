package com.jade.common.core.util;

import cn.hutool.core.util.StrUtil;
import com.jade.common.core.constant.CommonConstant;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class TokenUtil {

    public static String getToken() {
        return getToken(ServletUtil.getRequest());
    }

    public static String getToken(HttpServletRequest request) {
        // 从header获取token标识
        String token = request.getHeader(CommonConstant.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    public static String replaceTokenPrefix(String token) {
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StrUtil.isNotEmpty(token) && token.startsWith(CommonConstant.PREFIX)) {
            token = token.replaceFirst(CommonConstant.PREFIX, "");
        }
        return token;
    }

}
