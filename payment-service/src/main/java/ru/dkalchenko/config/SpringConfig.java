package ru.dkalchenko.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class SpringConfig {

    @Bean
    public RestClient restClient(@Value("${services.product.url}") String url) {
        return RestClient.builder()
                .baseUrl(url)
                .build();
    }
}
