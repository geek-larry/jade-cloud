package com.jade.system.api.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogOperDTO {

    private String status;

    private LocalDateTime[] operTime;

}
