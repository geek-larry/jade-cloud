package com.jade.system.biz.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jade.system.api.domain.SysLogOper;
import com.jade.system.api.domain.dto.LogOperDTO;
import com.jade.system.biz.mapper.SysLogOperMapper;
import com.jade.system.biz.service.SysLogOperService;
import org.springframework.stereotype.Service;

@Service
public class SysLogOperServiceImpl extends ServiceImpl<SysLogOperMapper, SysLogOper> implements SysLogOperService {

    @Override
    public Page<SysLogOper> getLogByPage(Page<SysLogOper> page, LogOperDTO logOperDTO) {
        return baseMapper.selectPage(page, buildQueryWrapper(logOperDTO));
    }

    private LambdaQueryWrapper<SysLogOper> buildQueryWrapper(LogOperDTO logOperDTO) {
        LambdaQueryWrapper<SysLogOper> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(logOperDTO.getStatus())) {
            wrapper.eq(SysLogOper::getStatus, logOperDTO.getStatus());
        }
        if (ArrayUtil.isNotEmpty(logOperDTO.getOperTime())) {
            wrapper.ge(SysLogOper::getOperTime, logOperDTO.getOperTime()[0]).le(SysLogOper::getOperTime,
                    logOperDTO.getOperTime()[1]);
        }
        return wrapper;
    }

}