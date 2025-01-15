package com.microservices.api_service.service;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.api_service.entity.Beer;

public class ExternalApiService {

    private static final String API_URL = "https://api.sampleapis.com/beers/ale";

    private final WebClient webClient;

    public ExternalApiService(WebClient.Builder webClienBuilder) {
        this.webClient = webClienBuilder.baseUrl(API_URL).build();
    }

    public List<Beer> fetchBeersFromApi() {
        return webClient.get().retrieve().bodyToFlux(Beer.class).collectList().block();
    }
}
