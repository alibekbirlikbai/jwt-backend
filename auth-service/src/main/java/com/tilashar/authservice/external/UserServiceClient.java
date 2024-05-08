package com.tilashar.authservice.external;

import com.tilashar.authservice.model.RegisterRequest;
//import com.tilashar.authservice.model.dto.RoleDTO;
import com.tilashar.authservice.model.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserServiceClient {
    private final WebClient webClient;

    @Autowired
    public UserServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .filter((request, next) -> {
                    System.out.println("Request: " + request.method() + " " + request.url());
                    return next.exchange(request);
                })
                .build();
    }

    // Методы для запросов к user-service
}
