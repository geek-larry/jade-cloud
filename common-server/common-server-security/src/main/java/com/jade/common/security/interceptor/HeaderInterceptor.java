package com.jade.common.security.interceptor;

import com.jade.common.core.context.SecurityContextHolder;
import com.jade.common.core.util.TokenUtil;
import com.jade.common.security.constant.SecurityConstant;
import com.jade.common.security.domain.LoginUserDetail;
import com.jade.common.security.util.AuthUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class HeaderInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
            @Nullable Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = TokenUtil.getToken();
        if (StringUtils.isNotEmpty(token)) {
            LoginUserDetail loginUser = AuthUtil.getLoginUser(token);
            if (!Objects.isNull(loginUser)) {
                AuthUtil.verifyLoginUserExpire(loginUser);
                SecurityContextHolder.set(SecurityConstant.LOGIN_USER, loginUser);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
            @Nullable Object handler, Exception ex) {
        SecurityContextHolder.remove();
    }
}
