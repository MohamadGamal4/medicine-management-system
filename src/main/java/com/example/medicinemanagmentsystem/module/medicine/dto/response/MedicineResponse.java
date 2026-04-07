package com.example.medicinemanagmentsystem.module.medicine.dto.response;

import com.example.medicinemanagmentsystem.module.category.dto.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineResponse {
    private String id;
    private String name;
    private String scientificName;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private LocalDate expiryDate;
    private String manufacturer;
    private Boolean requiresPrescription;

    private CategoryResponse category;
}
