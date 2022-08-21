package com.jade.common.security.util;

import com.jade.common.core.context.SecurityContextHolder;
import com.jade.common.security.constant.SecurityConstant;
import com.jade.common.security.domain.LoginUserDetail;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityUtil {

    public LoginUserDetail getLoginUser() {
        return SecurityContextHolder.get(SecurityConstant.LOGIN_USER, LoginUserDetail.class);
    }

}
