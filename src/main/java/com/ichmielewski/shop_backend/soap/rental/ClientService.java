package com.ichmielewski.shop_backend.soap.rental;

import com.swiderski.rental_service.schema.client.*;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientSOAP soap;

    public ClientService() {
        soap = new ClientSoapService().getClientPort();
    }

    public ClientData getById(long id) {
        ClientRequest request = new ClientRequest();
        request.setId(id);

        return soap.getClient(request).getClient();
    }

    public PageableXml getAll(int pageNumber, int pageSize, String sortBy, ClientFilter clientFilter) {
        ClientListRequest request = new ClientListRequest();
        request.setPageNo(pageNumber);
        request.setPageSize(pageSize);
        request.setSortBy(sortBy);
        request.setClientFilter(clientFilter);

        return soap.getAllClients(request).getPage();
    }

    public ClientData add(ClientData client) {
        Client request = new Client();
        request.setClient(client);

        return soap.addClient(request).getClient();
    }

    public ClientData delete(long id) {
        ClientDeleteRequest request = new ClientDeleteRequest();
        request.setId(id);

        return soap.deleteClient(request).getClient();
    }

}
