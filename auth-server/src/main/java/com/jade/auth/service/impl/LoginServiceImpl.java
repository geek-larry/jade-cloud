package com.jade.auth.service.impl;

import cn.hutool.http.HttpStatus;
import com.jade.auth.exception.AuthException;
import com.jade.auth.service.LogService;
import com.jade.auth.service.LoginService;
import com.jade.common.security.domain.LoginUserDetail;
import com.jade.system.api.FeignUserService;
import com.jade.system.api.domain.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private final LogService logService;
    private final FeignUserService feignUserService;

    @Override
    public LoginUserDetail getLoginUser(String username, String password, Integer pattern) {
        UserInfo userInfo = feignUserService.getUserInfo(username).getData();
        if (userInfo == null || !ENCODER.matches(password, userInfo.getSysUser().getPassword())) {
            logService.fail(username);
            throw new AuthException("用户名或密码错误", HttpStatus.HTTP_INTERNAL_ERROR);
        }
        if (userInfo.getSysUser().getStatus().equals("1")) {
            throw new AuthException("账号已禁用");
        }
        return userConvert(userInfo);
    }

    private LoginUserDetail userConvert(UserInfo userInfo) {
        LoginUserDetail loginUser = new LoginUserDetail();
        loginUser.setUserId(userInfo.getSysUser().getUserId());
        loginUser.setUsername(userInfo.getSysUser().getUserName());
        loginUser.setRoles(userInfo.getRoles());
        loginUser.setPermissions(userInfo.getPermissions());
        return loginUser;
    }
}
