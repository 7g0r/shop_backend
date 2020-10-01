package com.ichmielewski.shop_backend.dto;

import java.math.BigDecimal;

public class WarehouseDTO extends AbstractDTO {

    private ProductDTO productDTO;
    private Integer quantity;
    private BigDecimal price;

    public WarehouseDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
