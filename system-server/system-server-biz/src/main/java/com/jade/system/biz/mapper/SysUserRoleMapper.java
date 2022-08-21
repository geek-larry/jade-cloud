package com.jade.system.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jade.system.api.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    Boolean deleteByUserId(@Param("userId") Long userId);

}
