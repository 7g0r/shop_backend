package com.ichmielewski.shop_backend.employee.login;

import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FeignErrorDecoder implements ErrorDecoder {
    private final LoginService service;
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Autowired
    public FeignErrorDecoder(LoginService service){
        this.service = service;
    }
    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.status() == 403) {
            service.login();

            Request request = response.request();

            Map<String, Collection<String>> headers = new HashMap<>(request.headers());
            Collection<String> cookie = new ArrayList<>();
            cookie.add(service.getCookie());
            headers.put("Cookie", cookie);

            Request newRequest = Request.create(request.httpMethod(), request.url(), headers, request.body(), request.charset(), request.requestTemplate());

            throw new RetryableException(403, response.reason(), newRequest.httpMethod(), null, newRequest);
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }


}
