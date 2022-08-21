package com.jade.auth.service.impl;

import com.jade.auth.domain.LdapUser;
import com.jade.auth.service.LdapService;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LdapServiceImpl implements LdapService {

    private final LdapTemplate ldapTemplate;

    @Override
    public boolean auth(String username, String password) {
        EqualsFilter filter = new EqualsFilter("sAMAccountName", username);
        return ldapTemplate.authenticate("", filter.toString(), password);
    }

    @Override
    public LdapUser search(String key) {
        LdapQuery query = LdapQueryBuilder.query().where("sAMAccountName").is(key);
        return ldapTemplate.findOne(query, LdapUser.class);
    }
}
