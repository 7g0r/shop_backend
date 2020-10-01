package com.ichmielewski.shop_backend.employee.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/positions")
public class PositionController extends AbstractController<PositionDto> {
    private final PositionClient client;


    @Autowired
    public PositionController(PositionClient client, PositionService service) {
        super(client, "position", service);
        this.client = client;
    }


}
