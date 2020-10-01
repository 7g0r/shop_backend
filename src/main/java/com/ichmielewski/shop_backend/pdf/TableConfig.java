package com.ichmielewski.shop_backend.pdf;

import com.google.common.primitives.Floats;
import com.ichmielewski.shop_backend.pdf.annotation.HeaderParam;
import com.ichmielewski.shop_backend.pdf.annotation.IgnoreField;
import com.ichmielewski.shop_backend.pdf.annotation.Title;
import com.itextpdf.text.BaseColor;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.WeakHashMap;
import java.util.stream.Collectors;


public class TableConfig {
    private static final WeakHashMap<Class<?>, TableConfig> CACHE = new WeakHashMap<>();

    private final TableColumnConfig[] columns;
    private final float[] widths;
    private final BaseColor headerColor;
    private final String title;

    private TableConfig(Class<?> type) {
        columns = buildColumns(type);
        widths = buildWidths();
        headerColor = buildHeaderColor(type);
        title = buildTitle(type);
    }

    private BaseColor buildHeaderColor(Class<?> type) {
        final BaseColor headerColor;
        HeaderParam annotation = type.getAnnotation(HeaderParam.class);
        if (annotation == null) {
            headerColor = new BaseColor(0.9f, 0.9f, 0.9f);
        } else {
            headerColor = Utils.asBaseColor(annotation.backgroundColor());
        }
        return headerColor;
    }

    private float[] buildWidths() {
        final float[] widths;
        long columnsWithoutDefinedWidth = Arrays.stream(columns).filter(column -> column.getWidth() == 0f).count();
        if (columnsWithoutDefinedWidth == 0) {
            columnsWithoutDefinedWidth = 1;
        }
        float defaultWidth = Arrays.stream(columns).map(TableColumnConfig::getWidth)
                .reduce(Float::sum)
                .map(sum -> 100f - sum)
                .orElse(0f) / columnsWithoutDefinedWidth;
        widths = Floats.toArray(Arrays.stream(columns).map(TableColumnConfig::getWidth)
                .map(width -> width == 0f ? defaultWidth : width)
                .collect(Collectors.toList()));
        return widths;
    }

    private TableColumnConfig[] buildColumns(Class<?> type) {
        final TableColumnConfig[] columns;
        Deque<Field> fields = new ArrayDeque<>();
        for (Class<?> superType = type; superType != null; superType = superType.getSuperclass()) {
            Field[] classFields = superType.getDeclaredFields();
            for (int i = classFields.length - 1; i >= 0; i--) {
                if (classFields[i].getAnnotation(IgnoreField.class) == null) {
                    fields.addFirst(classFields[i]);
                }
            }
        }

        columns = fields.stream().map(TableColumnConfig::new).toArray(TableColumnConfig[]::new);
        return columns;
    }

    private String buildTitle(Class<?> type) {
        Title annotation = type.getAnnotation(Title.class);
        if (annotation == null) {
            return type.getSimpleName();
        } else {
            return annotation.value();
        }
    }

    public TableColumnConfig[] getColumns() {
        return columns;
    }

    public int getSize() {
        return columns.length;
    }

    public float[] getWidths() {
        return widths;
    }

    public static TableConfig get(Class<?> type) {
        return CACHE.computeIfAbsent(type, TableConfig::new);
    }

    public BaseColor getHeaderColor() {
        return headerColor;
    }

    public String getTitle() {
        return title;
    }

}
