package com.jade.common.security.exception;

import org.apache.commons.lang3.StringUtils;

public class NotRoleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotRoleException(String role) {
        super(role);
    }

    public NotRoleException(String[] roles) {
        super(StringUtils.join(roles, ","));
    }
}
