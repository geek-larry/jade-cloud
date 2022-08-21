package com.jade.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jade.system.api.domain.SysGroupFile;

public interface SysGroupFileService extends IService<SysGroupFile> {

    Boolean saveGroupFiles(Long groupId, String menuIds);

}