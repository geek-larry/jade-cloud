package com.jade.auth.service;

public interface LogService {

    void logout(String loginName);

    void login(String loginName);

    void fail(String loginName);
}
