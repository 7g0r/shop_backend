package com.ichmielewski.shop_backend.employee.login;

import com.ichmielewski.shop_backend.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginDTO loginDto;
    private final LoginClient client;
    private String cookie;

    @Autowired
    public LoginService(@Value("${employee.user}") String user,
                        @Value("${employee.password}") String password,
                        LoginClient client) {
        this.loginDto = new LoginDTO(user, password);
        this.client = client;
    }

    public void login() {
        ResponseEntity<Void> response = client.login(loginDto);
        cookie = response.getHeaders().getFirst("set-cookie");
    }

    public String getCookie() {
        return cookie;
    }

}
