package com.ichmielewski.shop_backend.config;

import com.ichmielewski.shop_backend.audit.AuditAwareImpl;
import com.ichmielewski.shop_backend.soap.ProductCategorySoapController;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.xml.ws.Endpoint;
import java.util.concurrent.Executor;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ichmielewski.shop_backend.*")
@EnableJpaAuditing
@EnableAsync
public class Config {
    @Autowired
    private ProductCategorySoapController controller;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("shop");
        executor.initialize();
        return executor;
    }


    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditAwareImpl();
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), controller);
        endpoint.publish("/Categories");
        return endpoint;
    }

}

