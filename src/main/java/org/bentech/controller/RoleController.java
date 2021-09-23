package org.bentech.controller;

import org.bentech.dto.role.RoleCreateDto;
import org.bentech.dto.role.RoleDto;
import org.bentech.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    RolesService roleService;

    @GetMapping("api/role")
    public List<RoleDto> getAll() {
        return roleService.getAll();
    }

    @PostMapping("api/role")
    public RoleDto save(@RequestBody RoleCreateDto roleCreateDto) {
        return roleService.save(roleCreateDto);
    }
}
