package com.ichmielewski.shop_backend.dto;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    public LoginDTO() {}

    public LoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


