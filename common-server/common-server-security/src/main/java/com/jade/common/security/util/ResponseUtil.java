package com.jade.common.security.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.jade.common.core.domain.R;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    public static void responseExceptionError(HttpServletResponse response,
            Integer code,
            String message) throws IOException {
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(ContentType.JSON.toString());
        R<Object> objectR = R.restResult(null, code, message);
        String errorResponseJsonData = JSON.toJSONString(objectR);
        response.getWriter().write(errorResponseJsonData);
    }

}