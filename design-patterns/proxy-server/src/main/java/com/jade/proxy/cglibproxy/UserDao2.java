package com.jade.proxy.cglibproxy;

/**
 * 目标对象,没有实现任何接口
 */
public class UserDao2 {

    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
