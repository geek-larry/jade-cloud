package com.jade.system.biz.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jade.system.api.domain.SysMenu;

import java.util.List;
import java.util.Set;

public interface SysMenuService extends IService<SysMenu> {

    Set<SysMenu> findMenuByRoleId(Long roleId);

    Boolean removeMenuById(Long id);

    Boolean updateMenuById(SysMenu sysMenu);

    List<Tree<Long>> treeMenu(boolean lazy, Long parentId);

    List<Tree<Long>> filterMenu(Set<SysMenu> menuSet, Long parentId);

}