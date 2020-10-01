package com.ichmielewski.shop_backend.pdf.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Color {
    float r();
    float g();
    float b();

}
