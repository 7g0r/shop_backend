package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.dto.ProductDTO;
import com.ichmielewski.shop_backend.entity.AbstractEntity;
import com.ichmielewski.shop_backend.entity.ProductEntity;
import com.ichmielewski.shop_backend.mappers.ProductMapper;
import com.ichmielewski.shop_backend.param.ProductParam;
import com.ichmielewski.shop_backend.repository.ProductRepository;
import com.ichmielewski.shop_backend.resource.ReportService;
import com.ichmielewski.shop_backend.service.ProductService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends AbstractServiceImpl<ProductEntity, ProductDTO> implements ProductService {


    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ReportService reportService;



    public ProductServiceImpl(EntityManager entityManager,
                              ProductMapper productMapper,
                              ProductRepository productRepository,
                              ReportService reportService) {
        super(productRepository, productMapper, entityManager);
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.reportService = reportService;
    }

    @Override
    public byte[] getReport() {
        return reportService.buildReport("/productReport.jrxml");
    }



    @Override
    public Page<ProductDTO> getProducts(ProductParam param, Pageable pageable) {

        Specification<ProductEntity> specification = new Specification<ProductEntity>() {
            @Override
            public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (!StringUtils.isEmpty(param.getName())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + param.getName() + "%"));
                }
                if (!StringUtils.isEmpty(param.getDescription())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("description"), "%" + param.getDescription() + "%"));
                }
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("name")), criteriaBuilder.asc(root.get("id")));

                return predicate;
            }
        };

        Page<ProductEntity> productPage = productRepository.findAll(specification, pageable);
        List<ProductDTO> pageList = productMapper.mapToDtos(productPage.getContent());
        return new PageImpl<>(pageList, pageable, productPage.getTotalElements());
    }


    @Override
    public List<DictionaryDTO> getProductDictionary() {
        return productRepository.getProductDictionary();
    }

    @Override
    protected Class<? extends AbstractEntity> getEntityClass() {
        return ProductEntity.class;
    }
}
