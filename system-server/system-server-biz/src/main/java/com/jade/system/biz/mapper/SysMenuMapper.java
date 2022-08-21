package com.jade.system.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jade.system.api.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    Set<SysMenu> listMenusByRoleId(Long roleId);

}
