package com.ichmielewski.shop_backend.dto;

public class ProductDTO extends AbstractDTO {

    private String name;
    private String description;

    private ProductCategoryDTO categoryDTO;

    public ProductDTO() {
    }



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

    public ProductCategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(ProductCategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }
}
