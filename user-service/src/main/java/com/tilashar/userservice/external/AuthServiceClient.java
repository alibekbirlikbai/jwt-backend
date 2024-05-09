package com.tilashar.userservice.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthServiceClient {
    private final WebClient webClient;

    @Autowired
    public AuthServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .filter((request, next) -> {
                    System.out.println("Request: " + request.method() + " " + request.url());
                    System.out.println("Headers: " + request.headers());
                    return next.exchange(request);
                })
                .build();
    }
    
}
