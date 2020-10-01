package com.ichmielewski.shop_backend.pdf;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TableBuilder {

    public static PdfPTable build(Collection<?> rows) {
        if(rows.isEmpty()) {
            return new PdfPTable(1);
        }

        TableConfig config = TableConfig.get(rows.iterator().next().getClass());

        PdfPTable table = new PdfPTable(config.getWidths());
        table.setWidthPercentage(100);

        for (TableColumnConfig column : config.getColumns()) {
            PdfPCell cell = new PdfPCell(new Phrase(column.getName()));
            cell.setBackgroundColor(config.getHeaderColor());
            table.addCell(cell);
        }

        for (Object row : rows) {
            for (TableColumnConfig column : config.getColumns()) {
                PdfPCell cell = new PdfPCell(new Phrase(column.get(row)));
                cell.setBackgroundColor(column.getBackgroundColor());
                table.addCell(cell);
            }
        }

        return table;
    }

}
