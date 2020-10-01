package com.ichmielewski.shop_backend.dto;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDTO {


    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractDTO() {
    }
}
