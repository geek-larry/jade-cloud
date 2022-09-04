package com.jade.proxy.cglibproxy;

import com.jade.proxy.staticproxy.UserDao;
import org.junit.jupiter.api.Test;

/**
 * 测试类
 */
public class Start {

    @Test
    public void test(){
        //目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}


