package com.jade.system.api.domain.dto;

import com.jade.system.api.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleDTO extends SysUser {

    private Set<String> roles;

}
