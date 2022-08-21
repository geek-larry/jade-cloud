package com.jade.system.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jade.common.core.domain.R;
import com.jade.system.api.domain.SysLogOper;
import com.jade.system.api.domain.dto.LogOperDTO;
import com.jade.system.biz.service.SysLogOperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operlog")
public class LogOperController {

    private final SysLogOperService sysLogService;

    @GetMapping("/page")
    public R<IPage<SysLogOper>> getPage(Page<SysLogOper> page, LogOperDTO logOperDTO) {
        return R.ok(sysLogService.getLogByPage(page, logOperDTO));
    }

    @PostMapping
    public R<Boolean> save(@Valid @RequestBody SysLogOper sysLog) {
        return R.ok(sysLogService.save(sysLog));
    }

}