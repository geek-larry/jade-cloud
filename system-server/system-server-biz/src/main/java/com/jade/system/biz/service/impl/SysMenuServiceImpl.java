package com.jade.system.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jade.system.api.domain.SysMenu;
import com.jade.system.api.domain.SysRoleMenu;
import com.jade.system.biz.constant.MenuTypeEnum;
import com.jade.system.biz.mapper.SysMenuMapper;
import com.jade.system.biz.mapper.SysRoleMenuMapper;
import com.jade.system.biz.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Set<SysMenu> findMenuByRoleId(Long roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeMenuById(Long id) {
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));
        Assert.isTrue(CollUtil.isEmpty(menuList), "菜单存在下级节点 删除失败");
        sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
        return this.removeById(id);
    }

    @Override
    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }

    @Override
    public List<Tree<Long>> treeMenu(boolean lazy, Long parentId) {
        if (!lazy) {
            List<TreeNode<Long>> collect = baseMapper
                    .selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSortNum)).stream()
                    .map(getNodeFunction()).collect(Collectors.toList());
            return TreeUtil.build(collect, -1L);
        }
        Long parent = parentId == null ? -1L : parentId;
        List<TreeNode<Long>> collect = baseMapper
                .selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent)
                        .orderByAsc(SysMenu::getSortNum))
                .stream().map(getNodeFunction()).collect(Collectors.toList());

        return TreeUtil.build(collect, parent);
    }

    @Override
    public List<Tree<Long>> filterMenu(Set<SysMenu> all, Long parentId) {
        List<TreeNode<Long>> collect = all.stream()
                .filter(menu -> MenuTypeEnum.LEFT_MENU.getType().equals(menu.getType()))
                .filter(menu -> StrUtil.isNotBlank(menu.getRoute())).map(getNodeFunction())
                .collect(Collectors.toList());
        Long parent = parentId == null ? -1L : parentId;
        return TreeUtil.build(collect, parent);
    }

    @NotNull
    private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
        return menu -> {
            TreeNode<Long> node = new TreeNode<>();
            node.setId(menu.getMenuId());
            node.setName(menu.getMenuName());
            node.setParentId(menu.getParentId());
            node.setWeight(menu.getSortNum());
            Map<String, Object> extra = new HashMap<>();
            extra.put("route", menu.getRoute());
            extra.put("type", menu.getType());
            extra.put("permission", menu.getPermission());
            extra.put("status", menu.getStatus());
            node.setExtra(extra);
            return node;
        };
    }

}
