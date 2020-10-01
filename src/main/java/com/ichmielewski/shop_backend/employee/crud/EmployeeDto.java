package com.ichmielewski.shop_backend.employee.crud;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.pdf.annotation.*;

import java.util.Objects;

@Title("Employees")
@HeaderParam(backgroundColor = @Color(r = 0.7f, g = 0.7f, b = 0.7f))

public class EmployeeDto extends AbstractDTO {

    @ColumnParam(width = 20f, backgroundColor = @Color(r = 0.8f, g = 0.8f, b = 0.8f))
    private String name;
    @HeaderName("position")
    @IfNull("------")
    private PositionDto position;
    @HeaderName("manager")
    @IfNull("------")
    private ManagerDto manager;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!super.equals(obj) || !(obj instanceof EmployeeDto)) {
            return false;
        }

        EmployeeDto dto = (EmployeeDto)obj;

        return  Objects.equals(name, dto.name) &&
                Objects.equals(position, dto.position) &&
                Objects.equals(manager, dto.manager);
    }

    @Override
    public String toString() {
        return getName();
    }
}
