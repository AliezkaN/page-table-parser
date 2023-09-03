package com.aliezkan.pageparser.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Validated
public class HtmlFetchService {

    private final WebClient webClient;

    public Mono<String> fetchHtml(@NonNull String url){
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .cache();
    }
}
