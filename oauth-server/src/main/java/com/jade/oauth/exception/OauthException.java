package com.jade.oauth.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OauthException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    public OauthException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public OauthException(String message) {
        this(null, message);
    }

}
