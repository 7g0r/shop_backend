package com.ichmielewski.shop_backend.resource;

import com.ichmielewski.shop_backend.dto.ProductCategoryDTO;
import com.ichmielewski.shop_backend.entity.ProductCategoryEntity;
import com.ichmielewski.shop_backend.param.ProductCategoryParam;
import com.ichmielewski.shop_backend.service.AbstractService;
import com.ichmielewski.shop_backend.service.ProductCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/product-categories")
public class ProductCategoryResource extends AbstractResource<ProductCategoryEntity, ProductCategoryDTO> {

    private final ProductCategoryService productCategoryService;
    private final Logger log = LoggerFactory.getLogger(ProductCategoryResource.class);

    public ProductCategoryResource(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"categoryReport.pdf\"")
                .body(productCategoryService.getReport());
    }


    @GetMapping("/find")
    @PreAuthorize("hasAuthority('view')")
    public Page<ProductCategoryDTO> getCategories(@Valid @ModelAttribute ProductCategoryParam param,
                                                  @ApiIgnore @NonNull Pageable pageable) {
        return productCategoryService.getCategories(param, pageable);
    }

    @Override
    public AbstractService<ProductCategoryEntity, ProductCategoryDTO> getService() {
        return productCategoryService;
    }
}
