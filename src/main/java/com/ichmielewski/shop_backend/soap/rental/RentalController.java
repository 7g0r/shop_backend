package com.ichmielewski.shop_backend.soap.rental;

import com.swiderski.rental_service.schema.pageable.PageableXml;
import com.swiderski.rental_service.schema.rental.RentalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("rental/rental")

public class RentalController {

    private final RentalService service;

    @Autowired
    public RentalController(RentalService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public RentalData getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping
    public PageableXml getAll(@RequestParam("page") Integer pageNumber,
                              @RequestParam("size") Integer pageSize,
                              @RequestParam("sortBy") String sortBy) {

        return service.getAll(pageNumber, pageSize, sortBy);
    }

    @PostMapping
    public RentalData add(@RequestBody RentalData rental) {
        return service.add(rental);
    }

    @DeleteMapping("/{id}")
    public RentalData delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @PostMapping("/rent")
    public RentalData rentCar(@RequestParam("carId") long carId,
                              @RequestParam("clientId") long clientId) {
        return service.rentCar(carId, clientId);
    }

    @PostMapping("/return")
    public RentalData returnCar(@RequestParam("rentalId") long rentalId,
                                @RequestParam("rentalEnd") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate rentalEnd) {
        return service.returnCar(rentalId, rentalEnd);
    }


}
