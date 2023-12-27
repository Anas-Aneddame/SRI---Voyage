package com.example.sri_voyage_backend.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter {
    Double minBudget;
    Double maxBudget;
    String selectedCity;
}
