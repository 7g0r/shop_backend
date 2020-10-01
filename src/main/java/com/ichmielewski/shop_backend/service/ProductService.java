package com.ichmielewski.shop_backend.service;


import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.dto.ProductDTO;
import com.ichmielewski.shop_backend.entity.ProductEntity;
import com.ichmielewski.shop_backend.param.ProductParam;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductService extends AbstractService<ProductEntity, ProductDTO> {

    List<DictionaryDTO> getProductDictionary();


    Page<ProductDTO> getProducts(ProductParam param, Pageable pageable);



    byte[] getReport();
}

