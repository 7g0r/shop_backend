package com.ichmielewski.shop_backend.resource;

import com.ichmielewski.shop_backend.dto.ProductDTO;
import com.ichmielewski.shop_backend.entity.ProductEntity;
import com.ichmielewski.shop_backend.param.ProductParam;
import com.ichmielewski.shop_backend.service.AbstractService;
import com.ichmielewski.shop_backend.service.ProductService;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.FileNotFoundException;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/products")
public class ProductResource extends AbstractResource<ProductEntity, ProductDTO> {

    private final ProductService productService;
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/find")
    @PreAuthorize("hasAuthority('view')")
    public Page<ProductDTO> getProducts(@Valid @ModelAttribute ProductParam param,
                                        @ApiIgnore @NonNull Pageable pageable) {
        return productService.getProducts(param, pageable);
    }


    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"productReport.pdf\"")
                .body(productService.getReport());
    }


    @Override
    public AbstractService<ProductEntity, ProductDTO> getService() {
        return productService;
    }
}
