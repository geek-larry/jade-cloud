package com.jade.system.biz.controller;

import cn.hutool.core.lang.tree.Tree;
import com.jade.common.core.domain.R;
import com.jade.common.core.util.JwtUtil;
import com.jade.common.core.util.TokenUtil;
//import com.jade.common.security.annotation.RequiresPermissions;
import com.jade.system.api.domain.SysMenu;
import com.jade.system.biz.mapper.SysRoleMapper;
import com.jade.system.biz.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final SysMenuService sysMenuService;
    private final SysRoleMapper sysRoleMapper;

    @GetMapping
    public R<List<Tree<Long>>> getUserMenu(Long parentId) {
        // 获取符合条件的菜单
        String userId = JwtUtil.getUserId(TokenUtil.getToken());
        Set<SysMenu> menuSet = sysRoleMapper.listRolesByUserId(Long.valueOf(userId)).stream()
                .map(s -> sysMenuService.findMenuByRoleId(s.getRoleId())).flatMap(Collection::stream)
                .collect(Collectors.toSet());
        return R.ok(sysMenuService.filterMenu(menuSet, parentId));
    }

    @GetMapping(value = "/tree")
    public R<List<Tree<Long>>> tree(boolean lazy, Long parentId) {
        return R.ok(sysMenuService.treeMenu(lazy, parentId));
    }

    @GetMapping("/tree/{roleId}")
    public R<List<Long>> getByRoleId(@PathVariable Long roleId) {
        return R.ok(
                sysMenuService.findMenuByRoleId(roleId).stream().map(SysMenu::getMenuId).collect(Collectors.toList()));
    }

    @GetMapping("/{id:\\d+}")
    public R<SysMenu> getById(@PathVariable Long id) {
        return R.ok(sysMenuService.getById(id));
    }

    @PostMapping
    public R<SysMenu> save(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return R.ok(sysMenu);
    }

    @DeleteMapping("/{id:\\d+}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysMenuService.removeMenuById(id));
    }

    @PutMapping
    // @RequiresPermissions("sys_menu_update")
    public R<Boolean> update(@Valid @RequestBody SysMenu sysMenu) {
        return R.ok(sysMenuService.updateMenuById(sysMenu));
    }

}
