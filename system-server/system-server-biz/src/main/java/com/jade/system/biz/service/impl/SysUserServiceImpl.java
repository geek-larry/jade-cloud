package com.jade.system.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jade.system.api.domain.SysMenu;
import com.jade.system.api.domain.SysRole;
import com.jade.system.api.domain.SysUser;
import com.jade.system.api.domain.SysUserRole;
import com.jade.system.api.domain.dto.UserInfo;
import com.jade.system.api.domain.dto.UserRoleDTO;
import com.jade.system.api.domain.vo.UserVO;
import com.jade.system.biz.constant.MenuTypeEnum;
import com.jade.system.biz.mapper.SysRoleMapper;
import com.jade.system.biz.mapper.SysUserMapper;
import com.jade.system.biz.mapper.SysUserRoleMapper;
import com.jade.system.biz.service.SysMenuService;
import com.jade.system.biz.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final SysRoleMapper sysRoleMapper;

    private final SysMenuService sysMenuService;

    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(UserRoleDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
        baseMapper.insert(sysUser);
        userDto.getRoles().stream().map(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(Long.parseLong(roleId));
            return userRole;
        }).forEach(sysUserRoleMapper::insert);
        return Boolean.TRUE;
    }

    @Override
    public UserInfo getUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        // 设置角色列表
        List<SysRole> roleList = sysRoleMapper.listRolesByUserId(sysUser.getUserId());
        userInfo.setRoleList(roleList);
        // 设置角色列表 （ID）
        List<Long> roleIds = roleList.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        Set<String> roles = roleIds.stream().map(String::valueOf).collect(Collectors.toSet());
        userInfo.setRoles(roles);
        // 设置权限列表（menu.permission）
        Set<String> permissions = roleIds.stream().map(sysMenuService::findMenuByRoleId).flatMap(Collection::stream)
                .filter(m -> MenuTypeEnum.BUTTON.getType().equals(m.getType())).map(SysMenu::getPermission)
                .filter(StrUtil::isNotBlank).collect(Collectors.toSet());
        userInfo.setPermissions(permissions);
        return userInfo;
    }

    @Override
    public IPage<UserVO> getUserWithRolePage(Page<UserVO> page, UserRoleDTO userDTO) {
        return baseMapper.getUserVosPage(page, userDTO);
    }

    @Override
    public UserVO getUserVoById(Long id) {
        return baseMapper.getUserVoById(id);
    }

    @Override
    public Boolean removeUserById(SysUser sysUser) {
        sysUserRoleMapper.deleteByUserId(sysUser.getUserId());
        this.removeById(sysUser.getUserId());
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateUserInfo(UserRoleDTO userDto) {
        UserVO userVO = baseMapper.getUserVoByUsername(userDto.getUserName());
        Assert.isTrue(ENCODER.matches(userDto.getPassword(), userVO.getPassword()),
                "用户原密码错误，修改失败");
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userVO.getUserId());
        sysUser.setAvatar(userDto.getAvatar());
        return this.updateById(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(UserRoleDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setUpdateTime(LocalDateTime.now());

        if (StrUtil.isNotBlank(userDto.getPassword())) {
            sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
        }
        this.updateById(sysUser);

        sysUserRoleMapper
                .delete(Wrappers.<SysUserRole>update().lambda().eq(SysUserRole::getUserId, userDto.getUserId()));
        userDto.getRoles().forEach(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(Long.parseLong(roleId));
            userRole.setUserId(sysUser.getUserId());
            userRole.insert();
        });
        return Boolean.TRUE;
    }

}
