/*
 * @Author: songyx
 * @Date: 2022-08-14 11:58:38
 * @LastEditTime: 2022-08-20 23:27:10
 * @LastEditors: songyx 2250794569@qq.com
 * @Description: 
 */
package com.jade.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Demo {

    @GetMapping("test")
    public String test(){
        
        
        return "hello world";
        
    }

   

}
