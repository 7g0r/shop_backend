package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.UserDTO;
import com.ichmielewski.shop_backend.entity.UserEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.mappers.UserMapper;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import com.ichmielewski.shop_backend.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserServiceImplTest extends AbstractServiceImplTest<UserEntity, UserDTO> {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @Override
    AbstractServiceImpl<UserEntity, UserDTO> getService() {
        return userService;
    }

    @Override
    AbstractRepository<UserEntity> getRepository() {
        return userRepository;
    }

    @Override
    AbstractMapper<UserEntity, UserDTO> getMapper() {
        return userMapper;
    }

    @Override
    Class<UserDTO> getDtoClass() {
        return UserDTO.class;
    }

    @Override
    Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }
}
