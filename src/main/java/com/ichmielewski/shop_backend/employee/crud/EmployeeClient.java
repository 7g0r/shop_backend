package com.ichmielewski.shop_backend.employee.crud;

import com.ichmielewski.shop_backend.employee.login.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Repository;

@Repository
@FeignClient(value = "employees", url = "${employee.url}/employees", configuration = FeignClientConfiguration.class)
public interface EmployeeClient extends AbstractClient<EmployeeDto> {
}
