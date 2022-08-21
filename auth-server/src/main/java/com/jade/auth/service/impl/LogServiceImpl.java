package com.jade.auth.service.impl;

import com.jade.auth.constant.AuthConstant;
import com.jade.auth.service.LogService;
import com.jade.common.core.util.IpUtil;
import com.jade.common.core.util.ServletUtil;
import com.jade.system.api.FeignLogService;
import com.jade.system.api.domain.SysLogLogin;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final FeignLogService feignLogService;

    @Override
    public void logout(String loginName) {
        saveLog(loginName, AuthConstant.LOGOUT, "退出成功");
    }

    @Override
    public void login(String loginName) {
        saveLog(loginName, AuthConstant.LOGIN_SUCCESS, "登录成功");
    }

    @Override
    public void fail(String loginName) {
        saveLog(loginName, AuthConstant.LOGIN_FAIL, "登录失败");

    }

    public void saveLog(String username, String status, String message) {
        SysLogLogin logininfor = new SysLogLogin();
        logininfor.setUserName(username);
        logininfor.setLoginIp(IpUtil.getIpAddr(ServletUtil.getRequest()));
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, AuthConstant.LOGIN_SUCCESS, AuthConstant.LOGOUT)) {
            logininfor.setStatus(AuthConstant.LOGIN_SUCCESS_STATUS);
        } else if (AuthConstant.LOGIN_FAIL.equals(status)) {
            logininfor.setStatus(AuthConstant.LOGIN_FAIL_STATUS);
        }
        feignLogService.saveLogLogin(logininfor);
    }
}
