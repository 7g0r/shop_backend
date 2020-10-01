package com.ichmielewski.shop_backend.soap.rental;

import com.swiderski.rental_service.schema.car.CarData;
import com.swiderski.rental_service.schema.car.CarFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarsXlsxGenerator {
    private final CarService service;

    public CarsXlsxGenerator(CarService service) {
        this.service = service;
    }

    public ByteArrayOutputStream getCars() {
        List<CarData> cars = service.getAll(0, 20, "id", new CarFilter()).getContent().stream().map(car -> (CarData) car).collect(Collectors.toList());

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("cars");

        buildSheet(cars, sheet);

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            workbook.write(output);

            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void buildSheet(List<CarData> cars, XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("brand");
        row.createCell(2).setCellValue("model");
        row.createCell(3).setCellValue("model version");
        row.createCell(4).setCellValue("colour");
        row.createCell(5).setCellValue("engine type");

        for (CarData car : cars) {
            row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(car.getId());
            row.createCell(1).setCellValue(car.getBrand());
            row.createCell(2).setCellValue(car.getModel());
            row.createCell(3).setCellValue(car.getModelVersion());
            row.createCell(4).setCellValue(car.getColour());
            row.createCell(5).setCellValue(car.getEngineType());
        }
    }


}
