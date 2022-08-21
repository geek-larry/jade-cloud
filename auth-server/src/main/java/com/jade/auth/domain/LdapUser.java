package com.jade.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = { "person" })
public class LdapUser {

    @Id
    @JsonIgnore
    private Name distinguishedName;

    @Attribute(name = "sAMAccountName")
    private String loginName;

    @Attribute(name = "cn")
    private String username;

    @Attribute(name = "mail")
    private String email;

}
