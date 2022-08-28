package com.jade.elasticsearchdb.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class IndicesRecordDto implements Serializable {
    private String indexName;
    private Integer priNum;
    private Integer repliNum;
}
