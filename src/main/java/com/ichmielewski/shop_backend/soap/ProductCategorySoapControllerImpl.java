package com.ichmielewski.shop_backend.soap;

import com.ichmielewski.shop_backend.mappers.CategorySoapMapper;
import com.ichmielewski.shop_backend.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductCategorySoapControllerImpl implements ProductCategorySoapController {

    private ProductCategoryService service;
    private CategorySoapMapper mapper;

    @Autowired
    public ProductCategorySoapControllerImpl(ProductCategoryService service) {
        this.service = service;
        this.mapper = CategorySoapMapper.INSTANCE;
    }

    @Override
    public void deleteProductCategory(Long id) {
        service.delete(id);
    }

    @Override
    public ProductCategoryDTO getProductCategory(Long id) {
        return mapper.mapToDto(service.findById(id));
    }

    @Override
    public List<ProductCategoryDTO> getProductCategories() {
        return mapper.mapToDtos(service.getAll());
    }

    @Override
    public ProductCategoryDTO updateProductCategories(ProductCategoryDTO productCategory) {
        return mapper.mapToDto(service.update(productCategory.getId(), mapper.mapToEntity(productCategory)));
    }

    @Override
    public ProductCategoryDTO addProductCategory(ProductCategoryDTO productCategory) {
        return mapper.mapToDto(service.save(mapper.mapToEntity(productCategory)));
    }


}
