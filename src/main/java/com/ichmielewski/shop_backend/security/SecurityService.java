package com.ichmielewski.shop_backend.security;

import com.ichmielewski.shop_backend.dto.LoginDTO;
import com.ichmielewski.shop_backend.entity.UserEntity;
import com.ichmielewski.shop_backend.mappers.PermissionMapper;
import com.ichmielewski.shop_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SecurityService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionMapper mapper;

    public SecurityService(UserRepository repository, PasswordEncoder passwordEncoder, PermissionMapper mapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Autowired
    public SecurityService(UserRepository repository) {
        this.repository = repository;

        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        mapper = PermissionMapper.INSTANCE;
    }

    public boolean login(LoginDTO loginDto, HttpSession session) {
        UserEntity userSecure = repository.getUserByLogin(loginDto.getLogin());
        if (userSecure == null) {
            return false;
        }
        if (!passwordEncoder.matches(loginDto.getPassword(), userSecure.getPassword())) {
            return false;
        }

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(new UserAuthentication(userSecure));

        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return true;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
