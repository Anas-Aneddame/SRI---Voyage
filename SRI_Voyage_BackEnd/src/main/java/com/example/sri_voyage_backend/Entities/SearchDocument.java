package com.example.sri_voyage_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDocument {
    private String id;
    private String name;
//    private List<String> keywords;
    private String city;
    private String duration;
    private Double price;
    private String link;
    private String description;
    private String type;

}
