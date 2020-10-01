package com.ichmielewski.shop_backend.service;

import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.dto.ProductCategoryDTO;
import com.ichmielewski.shop_backend.entity.ProductCategoryEntity;
import com.ichmielewski.shop_backend.param.ProductCategoryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService extends AbstractService<ProductCategoryEntity, ProductCategoryDTO> {

    List<DictionaryDTO> getProductCategoriesDictionary();
    Page<ProductCategoryDTO> getCategories(ProductCategoryParam param, Pageable pageable);
    byte[] getReport();
}
