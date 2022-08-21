package com.jade.common.security.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class LoginUserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    private Long expireTime;

    private Long userId;

    private String username;

    private String loginIp;

    private Long loginTime;

    private Set<String> permissions;

    private Set<String> roles;

}