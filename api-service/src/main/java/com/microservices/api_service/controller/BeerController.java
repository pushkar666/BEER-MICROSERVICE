package com.microservices.api_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.api_service.entity.Beer;
import com.microservices.api_service.service.BeerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class BeerController {

    @Autowired
    BeerService beerService;

    @GetMapping("/beers")
    public ResponseEntity<List<Beer>> getAllbeers() {
        List<Beer> beers = beerService.getAllbeers();
        return ResponseEntity.ok(beers);
    }

    @PostMapping("/beers")
    public ResponseEntity<String> addBeer(@RequestBody Beer beer) {
        beerService.saveBeer(beer);
        return ResponseEntity.ok("Beer added successfully");
    }

    @PutMapping("beers/{id}")
    public ResponseEntity<String> updateBeerEntity(@PathVariable String id, @RequestBody Beer beer) {
        Boolean status = beerService.updateBeer(id, beer);
        if (status) {
            return ResponseEntity.ok("Beer entity updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
