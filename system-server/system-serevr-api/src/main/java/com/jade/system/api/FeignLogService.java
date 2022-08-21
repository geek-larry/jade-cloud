package com.jade.system.api;

import com.jade.common.core.domain.R;
import com.jade.common.security.feign.FeignRequestInterceptor;
import com.jade.system.api.domain.SysLogLogin;
import com.jade.system.api.domain.SysLogOper;
import com.jade.system.api.factory.FeignLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "feignLogService", value = "system-server", configuration = FeignRequestInterceptor.class, fallbackFactory = FeignLogFallbackFactory.class)
public interface FeignLogService {

    @PostMapping("/loginlog")
    R<Boolean> saveLogLogin(@RequestBody SysLogLogin sysLogLogin);

    @PostMapping("/operlog")
    R<Boolean> saveLogOper(@RequestBody SysLogOper sysLogOper);
}
