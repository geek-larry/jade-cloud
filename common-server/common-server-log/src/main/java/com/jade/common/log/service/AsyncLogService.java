package com.jade.common.log.service;

import com.jade.system.api.FeignLogService;
import com.jade.system.api.domain.SysLogOper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncLogService {

    private final FeignLogService feignLogService;

    @Async
    public void saveSysLog(SysLogOper sysLogOper) {
        feignLogService.saveLogOper(sysLogOper);
    }

}
