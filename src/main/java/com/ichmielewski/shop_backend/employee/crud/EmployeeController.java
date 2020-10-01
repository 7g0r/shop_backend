package com.ichmielewski.shop_backend.employee.crud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employees")
public class EmployeeController extends AbstractController<EmployeeDto> {
    private final EmployeeClient client;

    public EmployeeController(EmployeeClient client, EmployeeService service) {
        super(client, "employees", service);
        this.client = client;
    }
}
