package com.ichmielewski.shop_backend.repository;

import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends AbstractRepository<ProductCategoryEntity> {
    @Query("SELECT new com.ichmielewski.shop_backend.dto.DictionaryDTO(id, name) FROM ProductCategoryEntity")
    List<DictionaryDTO> getProductCategoriesDictionary();


}
