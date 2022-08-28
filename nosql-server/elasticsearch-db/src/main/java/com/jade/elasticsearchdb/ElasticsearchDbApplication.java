package com.jade.elasticsearchdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jade.elasticsearchdb.mapper")

public class ElasticsearchDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchDbApplication.class, args);
    }

}
