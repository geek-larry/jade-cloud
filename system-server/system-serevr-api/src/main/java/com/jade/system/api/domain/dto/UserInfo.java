package com.jade.system.api.domain.dto;

import com.jade.system.api.domain.SysRole;
import com.jade.system.api.domain.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private SysUser sysUser;

    private Set<String> permissions;

    private Set<String> roles;

    private List<SysRole> roleList;

}
