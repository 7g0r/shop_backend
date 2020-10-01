package com.ichmielewski.shop_backend.pdf;
import com.ichmielewski.shop_backend.pdf.annotation.Title;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.Collection;


public class PdfBuilder {
    public static byte[] build(Collection<?> rows) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, result);
            document.open();

            if(!rows.isEmpty()) {
                String title = getTitle(rows.iterator().next().getClass());

                document.addTitle(title);

                document.add(buildTitlePhrase(title));
                document.add(TableBuilder.build(rows));
            }
            else {
                document.add(buildTitlePhrase("empty"));
            }

            document.close();

            return result.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getTitle(Class<?> tableClass) {
        Title annotation = tableClass.getAnnotation(Title.class);
        if(annotation == null) {
            return tableClass.getSimpleName();
        }
        else {
            return annotation.value();
        }
    }

    private static Phrase buildTitlePhrase(String title) {
        Font font = new Font();
        font.setSize(30);

        return new Phrase(title, font);
    }

}
