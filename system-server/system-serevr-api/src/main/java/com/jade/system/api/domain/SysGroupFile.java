package com.jade.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysGroupFile extends Model<SysGroupFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "group_id", type = IdType.INPUT)
    private Long groupId;

    private Long fileId;

}
