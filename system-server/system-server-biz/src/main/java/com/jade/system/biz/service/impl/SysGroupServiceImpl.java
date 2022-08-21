package com.jade.system.biz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jade.system.api.domain.SysGroup;
import com.jade.system.api.domain.SysGroupFile;
import com.jade.system.biz.mapper.SysGroupFileMapper;
import com.jade.system.biz.mapper.SysGroupMapper;
import com.jade.system.biz.service.SysGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements SysGroupService {

    private final SysGroupFileMapper sysGroupFileMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeGroupById(Long id) {
        sysGroupFileMapper.delete(Wrappers.<SysGroupFile>update().lambda().eq(SysGroupFile::getGroupId, id));
        return this.removeById(id);
    }

}
