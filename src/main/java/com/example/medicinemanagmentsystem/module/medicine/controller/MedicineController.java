package com.example.medicinemanagmentsystem.module.medicine.controller;

import com.example.medicinemanagmentsystem.module.medicine.dto.request.MedicineRequest;
import com.example.medicinemanagmentsystem.module.medicine.dto.response.MedicineResponse;
import com.example.medicinemanagmentsystem.module.medicine.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/medicines")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineResponse> createMedicine(
            @Valid @RequestBody MedicineRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.createMedicine(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicineResponse>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponse> getMedicineById(@PathVariable UUID id) {
        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineResponse> updateMedicine(
            @PathVariable UUID id,
            @RequestBody MedicineRequest request) {
        return ResponseEntity.ok(medicineService.updateMedicine(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable UUID id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicineResponse>> searchByName(
            @RequestParam("medicine_name") String name) {
        return ResponseEntity.ok(medicineService.searchMedicinesByName(name));
    }

    @GetMapping("/expired")
    public ResponseEntity<List<MedicineResponse>> getExpiredMedicines() {
        return ResponseEntity.ok(medicineService.getExpiredMedicines());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<MedicineResponse>> getLowStockMedicines(
            @RequestParam(defaultValue = "10") Integer threshold) {
        return ResponseEntity.ok(medicineService.getLowStockMedicines(threshold));
    }

    // PATCH /api/v1/medicines/{id}/stock?quantity=50
    @PatchMapping("/{id}/stock")
    public ResponseEntity<MedicineResponse> updateStock(
            @PathVariable UUID id,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(medicineService.updateStock(id, quantity));
    }
}
