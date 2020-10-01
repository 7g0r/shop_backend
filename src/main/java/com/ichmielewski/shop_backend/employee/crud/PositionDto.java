package com.ichmielewski.shop_backend.employee.crud;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.pdf.annotation.Title;

@Title("Position")
public class PositionDto extends AbstractDTO {

    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
