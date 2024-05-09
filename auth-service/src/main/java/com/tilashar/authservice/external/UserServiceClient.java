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
    // http://localhost:8081/user-service/api/users
    public Mono<UserDetailsDTO> saveUser(RegisterRequest request) {
        return webClient.post()
                .uri(uriBuilder ->
                        uriBuilder.scheme("http")
                                .host("localhost")
                                .port(8081)
                                .path("/user-service/api/users")
                                .build())
                .body(BodyInserters.fromValue(request)) // Передаем объект запроса как тело запроса
                .retrieve()
                .bodyToMono(UserDetailsDTO.class);
    }

    // http://localhost:8081/user-service/api/users/{email}
    public Mono<RegisterRequest> findUser_byEmail(String email) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.scheme("http")
                                .host("localhost")
                                .port(8081)
                                .path("/user-service/api/users/" + email)
                                .build())
                .retrieve()
                .bodyToMono(RegisterRequest.class);
    }

}
