package com.ichmielewski.shop_backend.soap.rental;

import com.swiderski.rental_service.schema.client.ClientData;
import com.swiderski.rental_service.schema.client.ClientFilter;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rental/client")
public class ClientController {

    private final ClientService service;


    public ClientController(ClientService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ClientData getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping
    public PageableXml getAll(@RequestParam("page") Integer pageNumber,
                              @RequestParam("size") Integer pageSize,
                              @RequestParam("sortBy") String sortBy,
                              @RequestParam(value = "filter.name", required = false) String clientFilterName,
                              @RequestParam(value = "filter.surname", required = false) String clientFilterSurname,
                              @RequestParam(value = "filter.email", required = false) String clientFilterEmail,
                              @RequestParam(value = "filter.city", required = false) String clientFilterCity,
                              @RequestParam(value = "filter.street", required = false) String clientFilterStreet,
                              @RequestParam(value = "filter.zipCode", required = false) String clientFilterZipCode,
                              @RequestParam(value = "filter.phone", required = false) String clientFilterPhone) {

        ClientFilter clientFilter = new ClientFilter();
        clientFilter.setName(clientFilterName);
        clientFilter.setSurname(clientFilterSurname);
        clientFilter.setEmail(clientFilterEmail);
        clientFilter.setCity(clientFilterCity);
        clientFilter.setStreet(clientFilterStreet);
        clientFilter.setZipCode(clientFilterZipCode);
        clientFilter.setPhone(clientFilterPhone);

        return service.getAll(pageNumber, pageSize, sortBy, clientFilter);
    }

    @PostMapping
    public ClientData add(@RequestBody ClientData client) {
        return service.add(client);
    }

    @DeleteMapping("/{id}")
    public ClientData delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

}
