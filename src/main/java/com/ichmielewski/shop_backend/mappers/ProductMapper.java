package com.ichmielewski.shop_backend.mappers;

import com.ichmielewski.shop_backend.dto.ProductDTO;
import com.ichmielewski.shop_backend.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper extends AbstractMapper<ProductEntity, ProductDTO> {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "category", target = "categoryDTO")
    ProductDTO mapToDto(ProductEntity entity);

    @Mapping(source = "categoryDTO", target = "category")
    ProductEntity mapToEntity(ProductDTO dto);

    default List<ProductEntity> mapToEntieties(List<ProductDTO> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    default List<ProductDTO> mapToDtos(List<ProductEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
