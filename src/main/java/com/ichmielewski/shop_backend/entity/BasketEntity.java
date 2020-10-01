package com.ichmielewski.shop_backend.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "basket")
@Audited
public class BasketEntity extends AbstractEntity {

    private BigDecimal product;
    private Integer quantity;

    public BigDecimal getProduct() {
        return product;
    }

    public void setProduct(BigDecimal product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
