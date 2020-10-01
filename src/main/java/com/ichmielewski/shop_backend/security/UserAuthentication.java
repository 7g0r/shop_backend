package com.ichmielewski.shop_backend.security;

import com.ichmielewski.shop_backend.entity.UserEntity;
import com.ichmielewski.shop_backend.mappers.PermissionMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class UserAuthentication implements Authentication {
    private final UserEntity user;
    private final Set<PermissionAuthority> authorities;
    private final PermissionMapper mapper = PermissionMapper.INSTANCE;

    public UserAuthentication(UserEntity user) {
        this.user = user;
        authorities = mapper.toAuthority(user.getRole().getPermissions());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getLogin();
    }
}
