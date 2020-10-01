package com.ichmielewski.shop_backend.pdf;

import com.ichmielewski.shop_backend.pdf.annotation.Color;
import com.itextpdf.text.BaseColor;

public class Utils {
    public static BaseColor asBaseColor(Color color) {
        return new BaseColor(color.r(), color.g(), color.b());
    }
}


