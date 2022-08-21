package com.jade.system.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jade.common.core.domain.R;
import com.jade.common.log.annotation.Log;
import com.jade.system.api.domain.SysRole;
import com.jade.system.api.domain.dto.RoleMenuDTO;
import com.jade.system.biz.service.SysRoleMenuService;
import com.jade.system.biz.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final SysRoleService sysRoleService;

    private final SysRoleMenuService sysRoleMenuService;

    @GetMapping("/{id:\\d+}")
    public R<SysRole> getById(@PathVariable Long id) {
        return R.ok(sysRoleService.getById(id));
    }

    @Log(title = "添加角色")
    @PostMapping
    public R<Boolean> save(@Valid @RequestBody SysRole sysRole) {
        return R.ok(sysRoleService.save(sysRole));
    }

    @Log(title = "修改角色")
    @PutMapping
    public R<Boolean> update(@Valid @RequestBody SysRole sysRole) {
        return R.ok(sysRoleService.updateById(sysRole));
    }

    @Log(title = "删除角色")
    @DeleteMapping("/{id:\\d+}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysRoleService.removeRoleById(id));
    }

    @GetMapping("/list")
    public R<List<SysRole>> list() {
        return R.ok(sysRoleService.list(Wrappers.emptyWrapper()));
    }

    @GetMapping("/page")
    public R<IPage<SysRole>> getPage(Page<SysRole> page) {
        return R.ok(sysRoleService.page(page, Wrappers.emptyWrapper()));
    }

    @Log(title = "更新角色菜单")
    @PutMapping("/menu")
    public R<Boolean> assignMenu(@RequestBody RoleMenuDTO roleVo) {
        return R.ok(sysRoleMenuService.saveRoleMenus(roleVo.getRoleId(), roleVo.getMenus()));
    }

}
