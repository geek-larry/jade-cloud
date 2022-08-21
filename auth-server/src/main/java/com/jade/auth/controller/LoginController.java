package com.jade.auth.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.jade.auth.domain.LoginForm;
import com.jade.auth.service.LogService;
import com.jade.auth.service.LoginService;
import com.jade.common.core.domain.R;
import com.jade.common.core.util.JwtUtil;
import com.jade.common.core.util.TokenUtil;
import com.jade.common.security.domain.LoginUserDetail;
import com.jade.common.security.service.TokenService;
import com.jade.common.security.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final TokenService tokenService;

    private final LogService logService;

    // private final LdapService ldapService;

    private final LoginService loginService;

    public static void main(String[] args) {
        PasswordEncoder e = new BCryptPasswordEncoder();
        System.out.println(e.encode("admin"));
    }

    @PostMapping("login")
    public R<?> login(@RequestBody LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();
        LoginUserDetail loginUser = loginService.getLoginUser(username, password, 1);
        Map<String, Object> token = tokenService.createToken(loginUser);
        logService.login(username);
        return R.ok(token, "登录成功");
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request) {
        String token = TokenUtil.getToken(request);
        if (StrUtil.isNotEmpty(token)) {
            String username = JwtUtil.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            logService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request) {
        LoginUserDetail loginUser = tokenService.getLoginUser(request);
        if (!ObjectUtil.isNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

}