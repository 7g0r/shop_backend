package com.ichmielewski.shop_backend.mappers;

import com.ichmielewski.shop_backend.entity.Permission;
import com.ichmielewski.shop_backend.security.PermissionAuthority;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionAuthority toAuthority(Permission permission);

    default Set<PermissionAuthority> toAuthority(Set<Permission> permissions) {
        return permissions.stream().map(this::toAuthority).collect(Collectors.toSet());
    }
}

