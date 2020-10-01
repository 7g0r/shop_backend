package com.ichmielewski.shop_backend.soap.rental;


import com.swiderski.rental_service.schema.car.CarData;
import com.swiderski.rental_service.schema.car.CarFilter;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("rental/car")
public class CarController {
    private final CarService service;
    private final CarsXlsxGenerator xlsxGenerator;

    public CarController(CarService service, CarsXlsxGenerator xlsxGenerator) {
        this.service = service;
        this.xlsxGenerator = xlsxGenerator;
    }

    @GetMapping("/{id}")
    public CarData getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping
    public PageableXml getAll(@RequestParam("page") Integer pageNumber,
                              @RequestParam("size") Integer pageSize,
                              @RequestParam("sortBy") String sortBy,
                              @RequestParam(value = "filter.brand", required = false) String carFilterBrand,
                              @RequestParam(value = "filter.model", required = false) String carFilterModel,
                              @RequestParam(value = "filter.colour", required = false) String carFilterColour,
                              @RequestParam(value = "filter.yearFrom", required = false) BigInteger carFilterYearFrom,
                              @RequestParam(value = "filter.yearTo", required = false) BigInteger carFilterYearTo,
                              @RequestParam(value = "filter.engineType", required = false) String carFilterEngineType) {

        CarFilter clientFilter = new CarFilter();
        clientFilter.setBrand(carFilterBrand);
        clientFilter.setModel(carFilterModel);
        clientFilter.setColour(carFilterColour);
        clientFilter.setYearFrom(carFilterYearFrom);
        clientFilter.setYearTo(carFilterYearTo);
        clientFilter.setEngineType(carFilterEngineType);

        return service.getAll(pageNumber, pageSize, sortBy, clientFilter);
    }

    @PostMapping
    public CarData add(@RequestBody CarData car) {
        return service.add(car);
    }

    @DeleteMapping("/{id}")
    public CarData delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @GetMapping("/xlsx")
    public ResponseEntity<byte[]> getXlsx() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cars.xlsx\"")
                .body(xlsxGenerator.getCars().toByteArray());
    }


}
