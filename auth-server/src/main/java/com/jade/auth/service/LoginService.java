package com.jade.auth.service;

import com.jade.common.security.domain.LoginUserDetail;

public interface LoginService {

    LoginUserDetail getLoginUser(String username, String password, Integer pattern);

}
