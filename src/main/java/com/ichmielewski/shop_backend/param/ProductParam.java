package com.ichmielewski.shop_backend.param;

public class ProductParam {


    String name;
    String description;

    public ProductParam() {
    }

    public ProductParam(String name, String description) {
        this.name = name;
        this.description = description;
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
}
