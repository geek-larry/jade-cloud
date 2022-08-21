package com.jade.system.api.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserGroup extends Model<SysUserGroup> {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long groupId;

}
