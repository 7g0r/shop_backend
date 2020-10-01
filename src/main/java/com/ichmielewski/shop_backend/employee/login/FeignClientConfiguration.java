package com.ichmielewski.shop_backend.employee.login;

import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {
    @Bean
    public ErrorDecoder errorDecoder(LoginService service) {
        return new FeignErrorDecoder(service);
    }

    @Bean
    public RequestInterceptor requestInterceptor(LoginService service) {
        return new FeignRequestInterceptor(service);
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

}
