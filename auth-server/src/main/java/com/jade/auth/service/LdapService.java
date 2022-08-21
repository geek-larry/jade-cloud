package com.jade.auth.service;

import com.jade.auth.domain.LdapUser;

public interface LdapService {

    boolean auth(String username, String password);

    LdapUser search(String key);
}
