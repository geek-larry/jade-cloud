package com.jade.common.security.util;

import com.jade.common.security.annotation.RequiresPermissions;
import com.jade.common.security.annotation.RequiresRoles;
import com.jade.common.security.domain.LoginUserDetail;
import com.jade.common.security.service.AuthService;

public class AuthUtil {

    public static AuthService authService = new AuthService();

    /**
     * 会话注销
     */
    public static void logout() {
        authService.logout();
    }

    /**
     * 会话注销，根据指定Token
     */
    public static void logoutByToken(String token) {
        authService.logoutByToken(token);
    }

    /**
     * 检验当前会话是否已经登录，如未登录，则抛出异常
     */
    public static void checkLogin() {
        authService.checkLogin();
    }

    /**
     * 获取当前登录用户信息
     */
    public static LoginUserDetail getLoginUser(String token) {
        return authService.getLoginUser(token);
    }

    /**
     * 验证当前用户有效期
     */
    public static void verifyLoginUserExpire(LoginUserDetail loginUser) {
        authService.verifyLoginUserExpire(loginUser);
    }

    /**
     * 当前账号是否含有指定角色标识
     */
    public static boolean hasRole(String role) {
        return authService.hasRole(role);
    }

    /**
     * 当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException
     */
    public static void checkRole(String role) {
        authService.checkRole(role);
    }

    /**
     * 根据注解传入参数鉴权, 如果验证未通过，则抛出异常: NotRoleException
     */
    public static void checkRole(RequiresRoles requiresRoles) {
        authService.checkRole(requiresRoles);
    }

    /**
     * 当前账号是否含有指定角色标识 [指定多个，必须全部验证通过]
     */
    public static void checkRoleAnd(String... roles) {
        authService.checkRoleAnd(roles);
    }

    /**
     * 当前账号是否含有指定角色标识 [指定多个，只要其一验证通过即可]
     */
    public static void checkRoleOr(String... roles) {
        authService.checkRoleOr(roles);
    }

    /**
     * 当前账号是否含有指定权限, 返回true或false
     */
    public static boolean hasPermi(String permission) {
        return authService.hasPermi(permission);
    }

    /**
     * 当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
     */
    public static void checkPermi(String permission) {
        authService.checkPermi(permission);
    }

    /**
     * 根据注解传入参数鉴权, 如果验证未通过，则抛出异常: NotPermissionException
     */
    public static void checkPermi(RequiresPermissions requiresPermissions) {
        authService.checkPermi(requiresPermissions);
    }

    /**
     * 当前账号是否含有指定权限 [指定多个，必须全部验证通过]
     */
    public static void checkPermiAnd(String... permissions) {
        authService.checkPermiAnd(permissions);
    }

    /**
     * 当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
     */
    public static void checkPermiOr(String... permissions) {
        authService.checkPermiOr(permissions);
    }
}
