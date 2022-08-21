package com.jade.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 允许跨域的地址*表示所有
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 表示是否允许发送Cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许跨域的方法
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        // 预检请求的有效期,OPTIONS请求就是想服务端进行探测支持的方法
        response.setHeader("Access-Control-Max-Age", "3600");
        // 跨域时允许的请求头字段
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept,Token");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
