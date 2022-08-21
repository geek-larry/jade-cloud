package com.jade.system.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jade.system.api.domain.SysGroupFile;
import com.jade.system.biz.mapper.SysGroupFileMapper;
import com.jade.system.biz.service.SysGroupFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysGroupFileServiceImpl extends ServiceImpl<SysGroupFileMapper, SysGroupFile>
        implements SysGroupFileService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveGroupFiles(Long groupId, String menuIds) {
        this.remove(Wrappers.<SysGroupFile>query().lambda().eq(SysGroupFile::getGroupId, groupId));

        if (StrUtil.isBlank(menuIds)) {
            return Boolean.TRUE;
        }
        List<SysGroupFile> groupFileList = Arrays.stream(menuIds.split(StrUtil.COMMA)).map(menuId -> {
            SysGroupFile groupFile = new SysGroupFile();
            groupFile.setGroupId(groupId);
            groupFile.setFileId(Long.valueOf(menuId));
            return groupFile;
        }).collect(Collectors.toList());
        return this.saveBatch(groupFileList);
    }

}