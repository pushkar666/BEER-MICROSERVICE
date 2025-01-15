package com.microservices.api_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.microservices.api_service.entity.Beer;
import com.microservices.api_service.repository.BeerRepository;

public class BeerService {
    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ExternalApiService externalApiService;

    private final String BEER_CACHE = "BEER_CACHE";

    public List<Beer> getAllbeers() {
        // Check cache
        List<Beer> beers = (List<Beer>) redisTemplate.opsForValue().get(BEER_CACHE);
        if (beers == null) {
            // Fetch from MongoDB
            beers = beerRepository.findAll();

            // If MongoDB is empty, fetch from external API
            if (beers.isEmpty()) {
                beers = externalApiService.fetchBeersFromApi();
                beerRepository.saveAll(beers);
            }
            // Cache the result
            redisTemplate.opsForValue().set(BEER_CACHE, beers);
        }
        return beers;
    }

    public Beer saveBeer(Beer beer) {
        Beer saved = beerRepository.save(beer);
        redisTemplate.opsForValue().set(BEER_CACHE, beerRepository.findAll());
        return saved;
    }

    public Boolean updateBeer(String id, Beer beer) {
        Optional<Beer> optionalBeer = beerRepository.findById(id);
        if (optionalBeer.isPresent()) {
            Beer existingBeer = optionalBeer.get();
            existingBeer.setName(beer.getName());
            existingBeer.setPrice(beer.getPrice());
            existingBeer.setRating(beer.getRating());
            existingBeer.setImage(beer.getImage());
            beerRepository.save(existingBeer);
            redisTemplate.opsForValue().set(BEER_CACHE, beerRepository.findAll());
            return true;
        }
        return false;
    }
}