package com.ichmielewski.shop_backend.mappers;

import com.ichmielewski.shop_backend.dto.WarehouseDTO;
import com.ichmielewski.shop_backend.entity.WarehouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WarehouseMapper extends AbstractMapper<WarehouseEntity, WarehouseDTO> {

    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    @Mapping(source = "product", target = "productDTO")

    WarehouseDTO mapToDto(WarehouseEntity entity);

    @Mapping(source = "productDTO", target = "product")
    WarehouseEntity mapToEntity(WarehouseDTO dto);

    default List<WarehouseEntity> mapToEntieties(List<WarehouseDTO> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    default List<WarehouseDTO> mapToDtos(List<WarehouseEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
