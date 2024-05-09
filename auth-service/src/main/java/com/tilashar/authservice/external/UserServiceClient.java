package com.tilashar.authservice.external;

import com.tilashar.authservice.model.AuthRequest;
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
                    System.out.println("Headers: " + request.headers());
                    return next.exchange(request);
                })
                .build();
    }

    // Методы для запросов к user-service
    // http://localhost:8081/user-service/api/users
    public Mono<UserDetailsDTO> saveUser(AuthRequest request) {
        return webClient.post()
                .uri(uriBuilder ->
                        uriBuilder.scheme("http")
                                .host("localhost")
                                .port(8081)
                                .path("/user-service/api/users")
                                .build())
                .header("service", "auth-service")
                .body(BodyInserters.fromValue(request)) // Передаем объект запроса как тело запроса
                .retrieve()
                .bodyToMono(AuthRequest.class)
                .flatMap(user -> {
                    /* Обрабатываем полученные данные для работы c классами - auth-service
                     * (переводим поток данных из User.class -> в UserDetailsDTO.class)
                     */
                    UserDetailsDTO userDetailsDTO = UserDetailsDTO.builder()
                            .authRequest(user)
                            .build();

                    return Mono.just(userDetailsDTO);
                });
    }

    // http://localhost:8081/user-service/api/users/{email}
    public Mono<UserDetailsDTO> findUser_byEmail(String email) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.scheme("http")
                                .host("localhost")
                                .port(8081)
                                .path("/user-service/api/users/" + email)
                                .build())
                .header("service", "auth-service")
                .retrieve()
                .bodyToMono(AuthRequest.class)
                .flatMap(user -> {
                    /* Обрабатываем полученные данные для работы c классами - auth-service
                     * (переводим поток данных из User.class -> в UserDetailsDTO.class)
                     */
                    UserDetailsDTO userDetailsDTO = UserDetailsDTO.builder()
                            .authRequest(user)
                            .build();

                    return Mono.just(userDetailsDTO);
                });
    }

}
