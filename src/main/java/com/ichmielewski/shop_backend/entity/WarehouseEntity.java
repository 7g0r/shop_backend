package com.ichmielewski.shop_backend.entity;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stock")
@Audited
public class WarehouseEntity extends AbstractEntity {

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "product_Id")
    private ProductEntity productEntity;
    private Integer quantity;
    private BigDecimal price;

    public void setProduct(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public ProductEntity getProduct() {
        return productEntity;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
