package com.jade.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jade.system.api.domain.SysRoleMenu;

public interface SysRoleMenuService extends IService<SysRoleMenu> {

    Boolean saveRoleMenus(Long roleId, String menuIds);

}