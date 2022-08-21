package com.jade.system.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jade.system.api.domain.SysUser;
import com.jade.system.api.domain.dto.UserRoleDTO;
import com.jade.system.api.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserVO getUserVoByUsername(String username);

    IPage<UserVO> getUserVosPage(Page<UserVO> page, @Param("query") UserRoleDTO userDTO);

    UserVO getUserVoById(Long id);

    List<UserVO> selectVoList(@Param("query") UserRoleDTO userDTO);

}
