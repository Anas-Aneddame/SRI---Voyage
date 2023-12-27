package com.example.sri_voyage_backend.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.annotation.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter {
    Double minBudget;
    Double maxBudget;
    @Nullable
    String selectedCity;
    List<String> selectedActivities;
}
