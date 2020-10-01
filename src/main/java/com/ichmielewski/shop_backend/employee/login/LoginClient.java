package com.ichmielewski.shop_backend.employee.login;

import com.ichmielewski.shop_backend.dto.LoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "login", url = "${employee.url}/login")
public interface LoginClient {

    @PostMapping
    ResponseEntity<Void> login(@RequestBody LoginDTO loginDto);

}
