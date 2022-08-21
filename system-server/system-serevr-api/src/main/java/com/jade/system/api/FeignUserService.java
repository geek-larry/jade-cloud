package com.jade.system.api;

import com.jade.common.core.domain.R;
import com.jade.common.security.feign.FeignRequestInterceptor;
import com.jade.system.api.domain.dto.UserInfo;
import com.jade.system.api.factory.FeignUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "feignUserService", value = "system-server", configuration = FeignRequestInterceptor.class, fallbackFactory = FeignUserFallbackFactory.class)
public interface FeignUserService {

    @GetMapping("/user/info/{username}")
    R<UserInfo> getUserInfo(@PathVariable("username") String username);
}
