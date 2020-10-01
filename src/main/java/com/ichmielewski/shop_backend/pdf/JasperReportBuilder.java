package com.ichmielewski.shop_backend.pdf;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import java.io.InputStream;
import java.util.WeakHashMap;
import java.util.logging.Logger;


public class JasperReportBuilder {
    private static final WeakHashMap<String, JasperReport> CACHE = new WeakHashMap<>();

    private final Logger logger = Logger.getLogger(getClass().getName());

    public JasperReport get(String jrxml) {
        return CACHE.computeIfAbsent(jrxml, this::build);
    }

    private JasperReport build(String jrxml) {
        try {
            InputStream employeeReportStream = getClass().getResourceAsStream(jrxml);
            return JasperCompileManager.compileReport(employeeReportStream);
        } catch (JRException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

}
