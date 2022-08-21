package com.jade.auth.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;

    public AuthException(String message) {
        this.message = message;
    }

    public AuthException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

}
