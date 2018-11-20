package com.kenny.service.logistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Kenny on 2018/11/21.
 */
@Configuration
public class RestConfiguration {
    @Autowired
    RestTemplateBuilder builder;
    @Bean
    public RestTemplate restTemplate(){
        return builder.build();
    }
}
