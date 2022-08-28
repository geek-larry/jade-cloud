package com.jade.elasticsearchdb.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> implements Serializable {
    private int code;

    private T data;

    private String message;
}
