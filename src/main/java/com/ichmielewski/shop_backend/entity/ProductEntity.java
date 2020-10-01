package com.ichmielewski.shop_backend.entity;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Audited
public class ProductEntity extends AbstractEntity {


    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_Id")
    private ProductCategoryEntity category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryEntity productCategory) {
        this.category = productCategory;

    }
}
