package com.example.medicinemanagmentsystem.module.medicine.entity;

import com.example.medicinemanagmentsystem.module.category.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String scientificName;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private LocalDate expiryDate;
    private String manufacturer;
    private Boolean requiresPrescription;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
