package com.ichmielewski.shop_backend.soap.rental;

import com.swiderski.rental_service.schema.car.*;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarSOAP soap;

    public CarService() {
        soap = new CarSoapService().getCarSOAP();
    }

    public CarData getById(long id) {
        CarRequest request = new CarRequest();
        request.setId(id);

        return soap.getCar(request).getCar();
    }

    public PageableXml getAll(int pageNumber, int pageSize, String sortBy, CarFilter carFilter) {
        CarListRequest request = new CarListRequest();
        request.setPageNo(pageNumber);
        request.setPageSize(pageSize);
        request.setSortBy(sortBy);
        request.setCarFilter(carFilter);

        return soap.getAllCars(request).getPage();
    }

    public CarData add(CarData car) {
        Car request = new Car();
        request.setCar(car);

        return soap.addCar(request).getCar();
    }

    public CarData delete(long id) {
        CarDeleteRequest request = new CarDeleteRequest();
        request.setId(id);

        return soap.deleteCar(request).getCar();
    }

}
