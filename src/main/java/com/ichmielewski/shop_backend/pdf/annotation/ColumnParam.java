package com.ichmielewski.shop_backend.pdf.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnParam {
    float width() default 0;
    Color backgroundColor() default @Color(r = 1, g = 1, b = 1);

}
