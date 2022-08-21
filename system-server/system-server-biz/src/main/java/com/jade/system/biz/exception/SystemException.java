package com.jade.system.biz.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;

    public SystemException(String message) {
        this.message = message;
    }

}
