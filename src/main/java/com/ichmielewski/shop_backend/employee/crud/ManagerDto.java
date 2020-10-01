package com.ichmielewski.shop_backend.employee.crud;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.pdf.annotation.Title;

import java.util.Objects;

@Title("Manager")
public class ManagerDto extends AbstractDTO {

  private String name;


    public ManagerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!super.equals(obj) || !(obj instanceof ManagerDto)) {
            return false;
        }

        ManagerDto dto = (ManagerDto)obj;

        return  Objects.equals(name, dto.name);
    }

    @Override
    public String toString() {
        return getName();
    }
}

