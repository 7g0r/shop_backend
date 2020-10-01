package com.ichmielewski.shop_backend.soap.rental;

import com.swiderski.rental_service.schema.pageable.PageableXml;
import com.swiderski.rental_service.schema.rental.*;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;

@Service
public class RentalService {
    private final RentalSOAP soap;
    private final DatatypeFactory datatypeFactory;

    public RentalService() throws DatatypeConfigurationException {
        soap = new RentalSoapService().getRentalPort();
        datatypeFactory = DatatypeFactory.newInstance();
    }

    public RentalData getById(long id) {
        RentalRequest request = new RentalRequest();
        request.setId(id);

        return soap.rental(request).getRental();
    }

    public PageableXml getAll(int pageNumber, int pageSize, String sortBy) {
        RentalListRequest request = new RentalListRequest();
        request.setPageNo(pageNumber);
        request.setPageSize(pageSize);
        request.setSortBy(sortBy);

        return soap.rentals(request).getPage();
    }

    public RentalData add(RentalData rental) {
        Rental request = new Rental();
        request.setRental(rental);

        return soap.addRental(request).getRental();
    }

    public RentalData delete(long id) {
        RentalDeleteRequest request = new RentalDeleteRequest();
        request.setId(id);

        return soap.deleteRental(request).getRental();
    }

    public RentalData rentCar(long carId, long clientId) {
        CarRentRequest request = new CarRentRequest();
        request.setCarId(carId);
        request.setClientId(clientId);

        return soap.rentCar(request).getRental();
    }

    public RentalData returnCar(long rentalId, LocalDate returnedDate) {
        CarReturnRequest request = new CarReturnRequest();
        request.setRentalId(rentalId);
        request.setRentalEnd(datatypeFactory.newXMLGregorianCalendar(returnedDate.toString()));

        return soap.returnCar(request).getRental();
    }

}
