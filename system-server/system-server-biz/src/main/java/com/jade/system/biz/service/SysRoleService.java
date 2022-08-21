package com.jade.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jade.system.api.domain.SysRole;

public interface SysRoleService extends IService<SysRole> {

    Boolean removeRoleById(Long id);

}