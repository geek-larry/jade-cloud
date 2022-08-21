package com.jade.common.oauth.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.jade.common.oauth.domain.LoginUser;
import com.jade.common.oauth.util.SecurityUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;

@Service("ss")
public class PermissionService {

    private static final String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    /**
     * 验证用户是否具备某权限
     */
    public boolean hasPermi(String permission) {
        if (StrUtil.isEmpty(permission)) {
            return false;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (ObjectUtil.isEmpty(loginUser)
                || CollectionUtils.isEmpty(Objects.requireNonNull(loginUser).getAuthorities())) {
            return false;
        }
        return hasPermissions(loginUser.getAuthorities(), permission);
    }

    /**
     * 验证用户是否不具备某权限，与 hasPermi逻辑相反
     */
    public boolean lacksPermi(String permission) {
        return !hasPermi(permission);
    }

    /**
     * 验证用户是否具有以下任意一个权限
     *
     * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符的权限列表
     * @return 用户是否具有以下任意一个权限
     */
    public boolean hasAnyPermi(String permissions) {
        if (StrUtil.isEmpty(permissions)) {
            return false;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (ObjectUtil.isEmpty(loginUser)
                || CollectionUtils.isEmpty(Objects.requireNonNull(loginUser).getAuthorities())) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        for (String permission : permissions.split(PERMISSION_DELIMETER)) {
            if (permission != null && hasPermissions(authorities, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户是否拥有某个角色
     */
    public boolean hasRole(String role) {
        if (StrUtil.isEmpty(role)) {
            return false;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (ObjectUtil.isEmpty(loginUser)
                || CollectionUtils.isEmpty(Objects.requireNonNull(loginUser).getAuthorities())) {
            return false;
        }
        for (GrantedAuthority authorities : loginUser.getAuthorities()) {
            String roleKey = authorities.getAuthority();
            if (SUPER_ADMIN.contains(roleKey) || roleKey.contains(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     */
    public boolean lacksRole(String role) {
        return !hasRole(role);
    }

    /**
     * 验证用户是否具有以下任意一个角色
     */
    public boolean hasAnyRoles(String roles) {
        if (StrUtil.isEmpty(roles)) {
            return false;
        }
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (ObjectUtil.isEmpty(loginUser)
                || CollectionUtils.isEmpty(Objects.requireNonNull(loginUser).getAuthorities())) {
            return false;
        }
        for (String role : roles.split(ROLE_DELIMETER)) {
            if (hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否包含权限
     */
    private boolean hasPermissions(Collection<? extends GrantedAuthority> authorities, String permission) {
        return authorities.stream().map(GrantedAuthority::getAuthority).filter(StringUtils::hasText)
                .anyMatch(x -> ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(permission, x));
    }
}
