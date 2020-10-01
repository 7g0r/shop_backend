package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.ProductCategoryDTO;
import com.ichmielewski.shop_backend.entity.ProductCategoryEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.mappers.ProductCategoryMapper;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import com.ichmielewski.shop_backend.repository.ProductCategoryRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ProductCategoryServiceImplTest extends AbstractServiceImplTest<ProductCategoryEntity, ProductCategoryDTO>{

    @InjectMocks
    private ProductCategoryServiceImpl categoryService;
    @Mock
    private ProductCategoryRepository categoryRepository;
    @Mock
    private ProductCategoryMapper categoryMapper;


    @Override
    AbstractServiceImpl<ProductCategoryEntity, ProductCategoryDTO> getService() {
        return categoryService;
    }

    @Override
    AbstractRepository<ProductCategoryEntity> getRepository() {
        return categoryRepository;
    }

    @Override
    AbstractMapper<ProductCategoryEntity, ProductCategoryDTO> getMapper() {
        return categoryMapper;
    }

    @Override
    Class<ProductCategoryDTO> getDtoClass() {
        return ProductCategoryDTO.class;
    }

    @Override
    Class<ProductCategoryEntity> getEntityClass() {
        return ProductCategoryEntity.class;
    }
}
