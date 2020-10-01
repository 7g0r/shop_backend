package com.ichmielewski.shop_backend.mappers;


import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.entity.AbstractEntity;

import java.util.List;
import java.util.stream.Collectors;


public interface AbstractMapper<ENTITY , DTO > {


    DTO mapToDto(ENTITY entity);

    ENTITY mapToEntity(DTO dto);

    default List<ENTITY> mapToEntieties(List<DTO> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    default List<DTO> mapToDtos(List<ENTITY> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}

