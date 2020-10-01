package com.ichmielewski.shop_backend.resource;

import com.ichmielewski.shop_backend.pdf.JasperReportBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;


@Service
public class ReportService {
    private final DataSource dataSource;
    private final JasperReportBuilder reportBuilder;

    @Autowired
    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
        reportBuilder = new JasperReportBuilder();
    }

    public byte[] buildReport(String jrxml) {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportBuilder.get(jrxml), null, dataSource.getConnection());

            JRPdfExporter exporter = new JRPdfExporter();
            ByteArrayOutputStream result = new ByteArrayOutputStream();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(result));

            exporter.exportReport();

            return result.toByteArray();
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
