package com.ichmielewski.shop_backend.dto;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProductCategory")
public class ProductCategoryDTO extends AbstractDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategoryDTO() {
    }
}
