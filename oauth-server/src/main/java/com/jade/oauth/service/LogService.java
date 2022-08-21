package com.jade.oauth.service;

public interface LogService {

    void logout(String loginName);

    void login(String loginName);

    void fail(String loginName);
}