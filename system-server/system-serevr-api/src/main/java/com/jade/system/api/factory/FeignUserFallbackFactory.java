package com.jade.system.api.factory;

import com.jade.common.core.domain.R;
import com.jade.system.api.FeignUserService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignUserFallbackFactory implements FallbackFactory<FeignUserService> {

    @Override
    public FeignUserService create(Throwable throwable) {
        return username -> R.fail("获取用户失败:" + throwable.getMessage());
    }
}
