package com.ichmielewski.shop_backend.employee.crud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends AbstractService<EmployeeDto> {
    public EmployeeService(EmployeeClient client, JavaMailSender mailSender, @Value("${spring.mail.username}") String mailFrom) {
        super(client, mailSender, "employee", mailFrom);
    }
}
