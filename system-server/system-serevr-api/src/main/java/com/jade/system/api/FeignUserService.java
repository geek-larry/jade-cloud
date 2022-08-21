package com.jade.system.api;

import com.jade.common.core.domain.R;
import com.jade.system.api.domain.dto.UserInfo;
import com.jade.system.api.factory.FeignUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "feignUserService", value = "system-server", fallbackFactory = FeignUserFallbackFactory.class)
public interface FeignUserService {
    // configuration = FeignRequestInterceptor.class,
    @GetMapping("/user/info/{username}")
    R<UserInfo> getUserInfo(@PathVariable("username") String username);
}
