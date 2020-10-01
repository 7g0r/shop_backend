package com.ichmielewski.shop_backend.entity;


import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Audited
public class ProductCategoryEntity extends AbstractEntity {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
