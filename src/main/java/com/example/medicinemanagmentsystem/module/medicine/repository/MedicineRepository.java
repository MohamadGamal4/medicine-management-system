package com.example.medicinemanagmentsystem.module.medicine.repository;

import com.example.medicinemanagmentsystem.module.medicine.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {


    List<Medicine> findByNameContainingIgnoreCase(String name);
    List<Medicine> findByExpiryDateBefore(LocalDate date);
    List<Medicine> findByQuantityInStockLessThanEqual(Integer threshold);
}