package com.ichmielewski.shop_backend.pdf;

import com.ichmielewski.shop_backend.pdf.annotation.ColumnParam;
import com.ichmielewski.shop_backend.pdf.annotation.HeaderName;
import com.ichmielewski.shop_backend.pdf.annotation.IfNull;
import com.itextpdf.text.BaseColor;
import java.lang.reflect.Field;


public class TableColumnConfig {
    private final Field field;
    private final String name;
    private final String ifNull;
    private final float width;
    private final BaseColor backgroundColor;

    public TableColumnConfig(Field field) {
        this.field = field;

        HeaderName headerName = field.getAnnotation(HeaderName.class);
        if(headerName == null) {
            name = field.getName();
        }
        else {
            name = headerName.value();
        }

        IfNull ifNullAnnotation = field.getAnnotation(IfNull.class);
        if(ifNullAnnotation == null) {
            ifNull = "";
        }
        else {
            ifNull = ifNullAnnotation.value();
        }

        ColumnParam columnParam = field.getAnnotation(ColumnParam.class);
        if(columnParam == null) {
            width = 0;
            backgroundColor = new BaseColor(1f, 1f, 1f);
        }
        else {
            width = columnParam.width();
            backgroundColor = Utils.asBaseColor(columnParam.backgroundColor());
        }

        field.setAccessible(true);
    }

    public String getName() {
        return name;
    }

    public String get(Object row) {
        try {
            Object value = field.get(row);
            if(value == null) {
                return ifNull;
            }
            else {
                return value.toString();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public float getWidth() {
        return width;
    }

    public BaseColor getBackgroundColor() {
        return backgroundColor;
    }

}
