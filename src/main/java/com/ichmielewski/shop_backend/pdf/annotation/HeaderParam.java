package com.ichmielewski.shop_backend.pdf.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HeaderParam {
    Color backgroundColor() default @Color(r = 0.9f, g = 0.9f, b = 0.9f);
}
