package com.jade.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jade.system.api.domain.SysGroup;

public interface SysGroupService extends IService<SysGroup> {

    Boolean removeGroupById(Long id);

}