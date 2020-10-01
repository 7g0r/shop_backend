package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.dto.ProductCategoryDTO;
import com.ichmielewski.shop_backend.entity.AbstractEntity;
import com.ichmielewski.shop_backend.entity.ProductCategoryEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.mappers.ProductCategoryMapper;
import com.ichmielewski.shop_backend.param.ProductCategoryParam;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import com.ichmielewski.shop_backend.repository.ProductCategoryRepository;
import com.ichmielewski.shop_backend.resource.ReportService;
import com.ichmielewski.shop_backend.service.ProductCategoryService;
import net.sf.jasperreports.engine.*;
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
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductCategoryServiceImpl extends AbstractServiceImpl<ProductCategoryEntity, ProductCategoryDTO> implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper categoryMapper;
    private final ReportService reportService;

    @Autowired
    private DataSource dataSource;


    public ProductCategoryServiceImpl(EntityManager entityManager,
                                      ProductCategoryRepository productCategoryRepository,
                                      ProductCategoryMapper categoryMapper,
                                      ReportService reportService) {
        super(productCategoryRepository, categoryMapper, entityManager);
        this.productCategoryRepository = productCategoryRepository;
        this.categoryMapper = categoryMapper;
        this.reportService = reportService;
    }
    @Override
    public byte[] getReport() {
        return reportService.buildReport("/categoryReport.jrxml");
    }

    @Override
    public List<DictionaryDTO> getProductCategoriesDictionary() {
        return productCategoryRepository.getProductCategoriesDictionary();
    }


    @Override
    public Page<ProductCategoryDTO> getCategories(ProductCategoryParam param, Pageable pageable) {

        Specification<ProductCategoryEntity> specification = new Specification<ProductCategoryEntity>() {
            @Override
            public Predicate toPredicate(Root<ProductCategoryEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (!StringUtils.isEmpty(param.getName())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + param.getName() + "%"));
                }
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("name")), criteriaBuilder.asc(root.get("id")));

                return predicate;
            }
        };

        Page<ProductCategoryEntity> categoryPage = productCategoryRepository.findAll(specification, pageable);
        List<ProductCategoryDTO> pageList = categoryMapper.mapToDtos(categoryPage.getContent());
        return new PageImpl<>(pageList, pageable, categoryPage.getTotalElements());


    }

    protected AbstractRepository<ProductCategoryEntity> repository() {
        return productCategoryRepository;
    }


    protected AbstractMapper<ProductCategoryEntity, ProductCategoryDTO> mapper() {
        return categoryMapper;
    }

    @Override
    protected Class<? extends AbstractEntity> getEntityClass() {
        return ProductCategoryEntity.class;
    }
}
