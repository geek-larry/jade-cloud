package com.jade.system.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jade.system.api.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> listRolesByUserId(Long userId);

}
