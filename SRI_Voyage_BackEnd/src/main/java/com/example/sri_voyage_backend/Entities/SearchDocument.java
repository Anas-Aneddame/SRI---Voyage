package com.example.sri_voyage_backend.Entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
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
    private String score;
}
