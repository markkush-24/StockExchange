package com.example.stockexchange.configuration;


import feign.okhttp.OkHttpClient;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class ClientConfiguration {

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, "localhost")
                        .option(USER, "postgres")
                        .option(PASSWORD, "superuser")
                        .option(DATABASE, "postgres")
                        .option(PORT, 2022)
                        .build());
    }
}
