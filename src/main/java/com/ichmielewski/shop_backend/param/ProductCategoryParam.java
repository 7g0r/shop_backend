package com.ichmielewski.shop_backend.param;

public class ProductCategoryParam {

    private String name;

    public ProductCategoryParam() {
    }

    public ProductCategoryParam(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
