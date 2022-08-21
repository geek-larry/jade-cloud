package com.jade.common.security.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.jade.common.core.constant.CommonConstant;
import com.jade.common.core.util.JwtUtil;
import com.jade.common.core.util.ServletUtil;
import com.jade.common.core.util.TokenUtil;
import com.jade.common.redis.util.RedisUtil;
import com.jade.common.security.service.TokenService;
import com.jade.common.security.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityRequestFilter extends OncePerRequestFilter {

    private final RedisUtil redisUtil;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
            @Nullable FilterChain filterChain) throws IOException, ServletException {
        doFilter(request, response, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String url = ServletUtil.getRequest().getRequestURL().toString();
        boolean login = url.endsWith("login") || url.contains("/user/info/") || url.endsWith("loginlog")
                || url.endsWith("/doc.html") || url.endsWith("/swagger-resources") || url.endsWith("/v2/api-docs")
                || url.endsWith("/webjars/css/") || url.endsWith("/webjars/js/");
        if (login) {
            System.out.println(url);
            filterChain.doFilter(request, response);
            return;
        }
        String token = TokenUtil.getToken();
        if (ObjectUtil.isNull(token)) {
            ResponseUtil.responseExceptionError(response, HttpStatus.HTTP_FORBIDDEN, "token为空");
            return;
        } else if (!JwtUtil.checkToken(token)) {
            ResponseUtil.responseExceptionError(response, HttpStatus.HTTP_FORBIDDEN, "token非法");
            return;
        }
        String value = JwtUtil.getValue(JwtUtil.parseToken(token), CommonConstant.USER_KEY);
        if (!redisUtil.hasKey(tokenService.getTokenKey(value))) {
            ResponseUtil.responseExceptionError(response, HttpStatus.HTTP_FORBIDDEN, "登录状态过期");
            return;
        }
        filterChain.doFilter(request, response);
    }

}