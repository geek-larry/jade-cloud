package com.jade.common.mybatis.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jade.common.core.util.JwtUtil;
import com.jade.common.core.util.TokenUtil;
import org.apache.ibatis.reflection.MetaObject;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

public class FillConfig implements MetaObjectHandler {

    private static void fillValIfNullByName(String fieldName, Object fieldVal, MetaObject metaObject, boolean isCover) {
        if (!metaObject.hasSetter(fieldName)) {
            return;
        }
        Object userSetValue = metaObject.getValue(fieldName);
        String setValueStr = StrUtil.str(userSetValue, Charset.defaultCharset());
        if (StrUtil.isNotBlank(setValueStr) && !isCover) {
            return;
        }
        metaObject.setValue(fieldName, fieldVal);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        fillValIfNullByName("createTime", now, metaObject, false);
        fillValIfNullByName("updateTime", now, metaObject, false);
        fillValIfNullByName("createBy", getUserName(), metaObject, false);
        fillValIfNullByName("updateBy", getUserName(), metaObject, false);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillValIfNullByName("updateTime", LocalDateTime.now(), metaObject, true);
        fillValIfNullByName("updateBy", getUserName(), metaObject, true);
    }

    private String getUserName() {
        String token = TokenUtil.getToken();
        if (StrUtil.isBlank(token)) {
            return null;
        }
        return JwtUtil.getUserName(token);
    }

}