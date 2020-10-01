package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.ProductDTO;
import com.ichmielewski.shop_backend.entity.ProductEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.mappers.ProductMapper;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import com.ichmielewski.shop_backend.repository.ProductRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest extends AbstractServiceImplTest<ProductEntity , ProductDTO> {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    @Override
    AbstractServiceImpl<ProductEntity, ProductDTO> getService() {
        return productService;
    }

    @Override
    AbstractRepository<ProductEntity> getRepository() {
        return productRepository;
    }

    @Override
    AbstractMapper<ProductEntity, ProductDTO> getMapper() {
        return productMapper;
    }

    @Override
    Class<ProductDTO> getDtoClass() {
        return ProductDTO.class;
    }

    @Override
    Class<ProductEntity> getEntityClass() {
        return ProductEntity.class;
    }
}
