package com.microservices.api_service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.api_service.entity.Beer;

public interface BeerRepository extends MongoRepository<Beer, String> {
    Optional<Beer> findByName(String name);
}
