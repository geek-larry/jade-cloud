package com.jade.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jade.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "group_id", type = IdType.AUTO)
    private Long groupId;

    // @NotBlank(message = "用户组名称 不能为空")
    private String groupName;

    // @NotBlank(message = "用户组标识 不能为空")
    private String groupCode;

    private String groupDesc;

    @TableLogic
    private String delFlag;

}
