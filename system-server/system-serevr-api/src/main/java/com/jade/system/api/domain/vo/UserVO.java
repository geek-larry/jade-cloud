package com.jade.system.api.domain.vo;

import com.jade.system.api.domain.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String realName;

    private String password;

    private String avatar;

    private String email;

    private String dept;

    private String status;

    private String delFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<SysRole> roleList;

}
