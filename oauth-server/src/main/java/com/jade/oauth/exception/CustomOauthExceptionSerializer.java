package com.jade.oauth.exception;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(CustomOauthExceptionSerializer.class);

    public static final String BAD_CREDENTIALS = "Bad credentials";

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", HttpStatus.HTTP_INTERNAL_ERROR);
        if (StrUtil.equals(e.getMessage(), BAD_CREDENTIALS)) {
            jsonGenerator.writeStringField("message", "用户名或密码错误");
        } else {
            log.warn("oauth2 认证异常 {:} ", e);
            jsonGenerator.writeStringField("message", e.getMessage());
        }
        jsonGenerator.writeEndObject();
    }
}