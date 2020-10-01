package com.ichmielewski.shop_backend.mappers;

import com.ichmielewski.shop_backend.dto.ProductCategoryDTO;
import com.ichmielewski.shop_backend.entity.ProductCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper extends AbstractMapper<ProductCategoryEntity, ProductCategoryDTO> {

    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    ProductCategoryDTO mapToDto(ProductCategoryEntity entity);

    ProductCategoryEntity mapToEntity(ProductCategoryDTO dto);

    default List<ProductCategoryEntity> mapToEntieties(List<ProductCategoryDTO> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    default List<ProductCategoryDTO> mapToDtos(List<ProductCategoryEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
