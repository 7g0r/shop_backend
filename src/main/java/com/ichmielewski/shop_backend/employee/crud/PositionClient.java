package com.ichmielewski.shop_backend.employee.crud;

import com.ichmielewski.shop_backend.employee.login.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "positions", url = "${employee.url}/positions", configuration = FeignClientConfiguration.class)
public interface PositionClient extends AbstractClient<PositionDto> {
}
