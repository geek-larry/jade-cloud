package com.jade.system.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jade.system.api.domain.SysUser;
import com.jade.system.api.domain.dto.UserInfo;
import com.jade.system.api.domain.dto.UserRoleDTO;
import com.jade.system.api.domain.vo.UserVO;

public interface SysUserService extends IService<SysUser> {

    UserInfo getUserInfo(SysUser sysUser);

    IPage<UserVO> getUserWithRolePage(Page<UserVO> page, UserRoleDTO userDTO);

    Boolean removeUserById(SysUser sysUser);

    Boolean updateUserInfo(UserRoleDTO userDto);

    Boolean updateUser(UserRoleDTO userDto);

    UserVO getUserVoById(Long id);

    Boolean saveUser(UserRoleDTO userDto);

}
