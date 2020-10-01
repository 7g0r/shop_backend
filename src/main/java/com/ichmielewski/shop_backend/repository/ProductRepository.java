package com.ichmielewski.shop_backend.repository;

import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.entity.ProductCategoryEntity;
import com.ichmielewski.shop_backend.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends AbstractRepository<ProductEntity> {

    @Query("SELECT new com.ichmielewski.shop_backend.dto.DictionaryDTO(id ,name) FROM ProductEntity")
    List<DictionaryDTO> getProductDictionary();

    List<ProductEntity> findAllByName(String name, Pageable pageable);



}
