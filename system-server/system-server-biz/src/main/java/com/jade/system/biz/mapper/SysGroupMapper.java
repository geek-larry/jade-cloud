package com.jade.system.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jade.system.api.domain.SysGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysGroupMapper extends BaseMapper<SysGroup> {

    List<SysGroup> listGroupsByUserId(Long userId);

}
