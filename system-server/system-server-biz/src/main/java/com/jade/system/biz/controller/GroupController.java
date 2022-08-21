package com.jade.system.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jade.common.core.domain.R;
import com.jade.system.api.domain.SysGroup;
import com.jade.system.api.domain.dto.GroupFileDTO;
import com.jade.system.biz.service.SysGroupFileService;
import com.jade.system.biz.service.SysGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final SysGroupService sysGroupService;

    private final SysGroupFileService sysGroupFileService;

    @GetMapping("/{id:\\d+}")
    public R<SysGroup> getById(@PathVariable Long id) {
        return R.ok(sysGroupService.getById(id));
    }

    @PostMapping
    public R<Boolean> save(@Valid @RequestBody SysGroup sysGroup) {
        return R.ok(sysGroupService.save(sysGroup));
    }

    @PutMapping
    public R<Boolean> update(@Valid @RequestBody SysGroup sysGroup) {
        return R.ok(sysGroupService.updateById(sysGroup));
    }

    @DeleteMapping("/{id:\\d+}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysGroupService.removeGroupById(id));
    }

    @GetMapping("/list")
    public R<List<SysGroup>> list() {
        return R.ok(sysGroupService.list(Wrappers.emptyWrapper()));
    }

    @GetMapping("/page")
    public R<IPage<SysGroup>> getPage(Page<SysGroup> page) {
        return R.ok(sysGroupService.page(page, Wrappers.emptyWrapper()));
    }

    @PutMapping("/menu")
    public R<Boolean> assignFile(@RequestBody GroupFileDTO groupFileDTO) {
        return R.ok(sysGroupFileService.saveGroupFiles(groupFileDTO.getGroupId(), groupFileDTO.getFileIds()));
    }

}
