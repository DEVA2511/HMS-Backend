package com.hms.appointment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class webClientConfig {

    @Bean
    public  WebClient.Builder getWebClientBuilder(){
        return WebClient.builder().defaultHeader("X-Secret-key","SECRET").filter(logRequest());
    }

    private ExchangeFilterFunction logRequest(){
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
            return  Mono.just(clientRequest);
        });
    }
}
