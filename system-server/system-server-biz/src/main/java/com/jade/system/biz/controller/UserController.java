package com.jade.system.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jade.common.core.domain.R;
import com.jade.common.core.util.JwtUtil;
import com.jade.common.core.util.TokenUtil;
import com.jade.system.api.domain.SysUser;
import com.jade.system.api.domain.dto.UserInfo;
import com.jade.system.api.domain.dto.UserRoleDTO;
import com.jade.system.api.domain.vo.UserVO;
import com.jade.system.biz.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {

    private final SysUserService userService;

    @GetMapping(value = { "/info" })
    public R<UserInfo> getUserInfo() {
        String username = JwtUtil.getUserName(TokenUtil.getToken());
        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUserName, username));
        if (user == null) {
            return R.fail("获取当前用户信息失败");
        }
        UserInfo userInfo = userService.getUserInfo(user);
        return R.ok(userInfo);
    }

    @GetMapping("/info/{username}")
    public R<UserInfo> getByName(@PathVariable String username) {
        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUserName, username));
        if (user == null) {
            return R.fail("用户信息为空");
        }
        return R.ok(userService.getUserInfo(user));
    }

    @GetMapping("/{id:\\d+}")
    public R<UserVO> getById(@PathVariable Long id) {
        return R.ok(userService.getUserVoById(id));
    }

    @DeleteMapping("/{id:\\d+}")
    public R<Boolean> remove(@PathVariable Long id) {
        SysUser sysUser = userService.getById(id);
        return R.ok(userService.removeUserById(sysUser));
    }

    @PostMapping
    public R<Boolean> save(@RequestBody UserRoleDTO userDto) {
        return R.ok(userService.saveUser(userDto));
    }

    @PutMapping
    public R<Boolean> update(@Valid @RequestBody UserRoleDTO userDto) {
        return R.ok(userService.updateUser(userDto));
    }

    @GetMapping("/page")
    public R<IPage<UserVO>> getPage(Page<UserVO> page, UserRoleDTO userDTO) {
        return R.ok(userService.getUserWithRolePage(page, userDTO));
    }

    @PutMapping("/edit")
    public R<Boolean> updateSelf(@Valid @RequestBody UserRoleDTO userDto) {
        return R.ok(userService.updateUserInfo(userDto));
    }

}
