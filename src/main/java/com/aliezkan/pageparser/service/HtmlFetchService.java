package com.aliezkan.pageparser.service;

import com.aliezkan.pageparser.exception.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

@Service
@RequiredArgsConstructor
@Validated
public class HtmlFetchService {

    private final WebClient webClient;

    public Mono<String> fetchHtml(@NonNull String url, @Nullable String authorizationToken) {
        System.out.println(authorizationToken);
        return webClient.get()
                .uri(url)
                .header("Authorization", authorizationToken)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::handleClientError)
                .bodyToMono(String.class)
                .cache();
    }

    private Mono<ServiceException> handleClientError(ClientResponse clientResponse) {
        switch (clientResponse.statusCode()){
            case NOT_FOUND: return Mono.error(PageNotFoundException::new);
            case UNAUTHORIZED: return Mono.error(UnauthorizedClientException::new);
            case FORBIDDEN: return Mono.error(ForbiddenClientException::new);
            default: return Mono.error(new CustomClientErrorException());
        }
    }
}
