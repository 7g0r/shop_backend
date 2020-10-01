package com.ichmielewski.shop_backend.mappers;

import com.ichmielewski.shop_backend.dto.UserDTO;
import com.ichmielewski.shop_backend.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<UserEntity, UserDTO> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);



    UserDTO mapToDto(UserEntity entity);


    UserEntity mapToEntity(UserDTO dto);


    default List<UserEntity> mapToEntieties(List<UserDTO> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    default List<UserDTO> mapToDtos(List<UserEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}

