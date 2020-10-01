package com.ichmielewski.shop_backend.security;

import com.ichmielewski.shop_backend.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class SecurityController {
    private final SecurityService service;

    @Autowired
    public SecurityController(SecurityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDto, HttpServletRequest request) {
        if(service.login(loginDto, request.getSession(true))) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/out")
    public void logout(HttpServletRequest request) {
        service.logout(request.getSession());
    }

}
