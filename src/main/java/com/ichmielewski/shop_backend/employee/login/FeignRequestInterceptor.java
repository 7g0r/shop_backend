package com.ichmielewski.shop_backend.employee.login;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignRequestInterceptor implements RequestInterceptor {
    private final LoginService service;

    public FeignRequestInterceptor(LoginService service) {
        this.service = service;
    }
    @Override
    public void apply(RequestTemplate template) {
        template.header("Cookie", service.getCookie());
    }

}
