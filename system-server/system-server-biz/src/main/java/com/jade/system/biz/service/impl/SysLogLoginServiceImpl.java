package com.jade.system.biz.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jade.system.api.domain.SysLogLogin;
import com.jade.system.api.domain.dto.LogLoginDTO;
import com.jade.system.biz.mapper.SysLogLoginMapper;
import com.jade.system.biz.service.SysLogLoginService;
import org.springframework.stereotype.Service;

@Service
public class SysLogLoginServiceImpl extends ServiceImpl<SysLogLoginMapper, SysLogLogin> implements SysLogLoginService {

    @Override
    public Page<SysLogLogin> getLogLoginByPage(Page<SysLogLogin> page, LogLoginDTO logLoginDTO) {
        return baseMapper.selectPage(page, buildQueryWrapper(logLoginDTO));
    }

    private LambdaQueryWrapper<SysLogLogin> buildQueryWrapper(LogLoginDTO logLoginDTO) {
        LambdaQueryWrapper<SysLogLogin> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(logLoginDTO.getStatus())) {
            wrapper.eq(SysLogLogin::getStatus, logLoginDTO.getStatus());
        }
        if (ArrayUtil.isNotEmpty(logLoginDTO.getLoginTime())) {
            wrapper.ge(SysLogLogin::getLoginTime, logLoginDTO.getLoginTime()[0]).le(SysLogLogin::getLoginTime,
                    logLoginDTO.getLoginTime()[1]);
        }
        return wrapper;
    }

}
