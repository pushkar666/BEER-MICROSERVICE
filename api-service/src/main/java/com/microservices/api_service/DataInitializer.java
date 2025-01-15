package com.microservices.api_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.microservices.api_service.service.BeerService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BeerService beerService;

    @Override
    public void run(String... args) {
        beerService.getAllbeers(); // Trigger the data fetch process
    }
}
