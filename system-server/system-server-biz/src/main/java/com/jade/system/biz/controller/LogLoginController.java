package com.jade.system.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jade.common.core.domain.R;
import com.jade.system.api.domain.SysLogLogin;
import com.jade.system.api.domain.dto.LogLoginDTO;
import com.jade.system.biz.service.SysLogLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loginlog")
public class LogLoginController {

    private final SysLogLoginService sysLogService;

    @GetMapping("/page")
    public R<IPage<SysLogLogin>> getPage(Page<SysLogLogin> page, LogLoginDTO logLoginDTO) {
        return R.ok(sysLogService.getLogLoginByPage(page, logLoginDTO));
    }

    @PostMapping
    public R<Boolean> save(@Valid @RequestBody SysLogLogin sysLogLogin) {
        sysLogLogin.setLoginTime(LocalDateTime.now());
        return R.ok(sysLogService.save(sysLogLogin));
    }

}