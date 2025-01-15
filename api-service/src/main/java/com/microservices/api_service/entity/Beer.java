package com.microservices.api_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "beers")
public class Beer {
    @Id
    private String id;
    private String name;
    private String price;
    private Rating rating;
    private String image;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Rating {
        private double average;
        private int reviews;
    }
}
