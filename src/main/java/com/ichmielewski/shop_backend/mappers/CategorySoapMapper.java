package com.ichmielewski.shop_backend.mappers;

import com.ichmielewski.shop_backend.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategorySoapMapper extends AbstractMapper<ProductCategoryDTO, com.ichmielewski.shop_backend.soap.ProductCategoryDTO> {

    CategorySoapMapper INSTANCE = Mappers.getMapper(CategorySoapMapper.class);
}
