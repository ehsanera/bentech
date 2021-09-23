package org.bentech.service;

import org.bentech.dto.role.RoleCreateDto;
import org.bentech.dto.role.RoleDto;
import org.bentech.entity.UserRolesEntity;
import org.bentech.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesService {

    @Autowired
    RolesRepository rolesRepository;

    public List<RoleDto> getAll() {
        return rolesRepository.findAll().stream().map(UserRolesEntity::toDto).collect(Collectors.toList());
    }

    public RoleDto save(RoleCreateDto roleCreateDto) {
        return rolesRepository.save(roleCreateDto.to()).toDto();
    }
}