package com.example.medicinemanagmentsystem.module.medicine.dto.request;

import com.example.medicinemanagmentsystem.module.category.entity.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {

    @NotBlank(message = "Medicine name is required")
    private String name;

    private String scientificName;
    private String description;

    @NotNull @DecimalMin(value = "0.0", message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull @Min(value = 0, message = "Quantity in stock must be non-negative")
    private Integer quantityInStock;

    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;

    private String manufacturer;
    private Boolean requiresPrescription;

    @NotNull
    private UUID categoryId;
}
