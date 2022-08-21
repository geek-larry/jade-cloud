package com.jade.system.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jade.system.api.domain.SysLogOper;
import com.jade.system.api.domain.dto.LogOperDTO;

public interface SysLogOperService extends IService<SysLogOper> {

    Page<SysLogOper> getLogByPage(Page<SysLogOper> page, LogOperDTO sysLog);

}
