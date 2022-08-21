package com.jade.system.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jade.system.api.domain.SysLogLogin;
import com.jade.system.api.domain.dto.LogLoginDTO;

public interface SysLogLoginService extends IService<SysLogLogin> {

    Page<SysLogLogin> getLogLoginByPage(Page<SysLogLogin> page, LogLoginDTO logLoginDTO);

}