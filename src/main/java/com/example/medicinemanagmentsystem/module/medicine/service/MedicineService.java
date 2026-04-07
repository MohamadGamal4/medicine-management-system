package com.example.medicinemanagmentsystem.module.medicine.service;

import com.example.medicinemanagmentsystem.module.medicine.dto.request.MedicineRequest;
import com.example.medicinemanagmentsystem.module.medicine.dto.response.MedicineResponse;

import java.util.List;
import java.util.UUID;

public interface MedicineService {
    MedicineResponse createMedicine(MedicineRequest request);
    List<MedicineResponse> getAllMedicines();
    MedicineResponse getMedicineById(UUID id);
    MedicineResponse updateMedicine(UUID id, MedicineRequest request);
    void deleteMedicine(UUID id);
    List<MedicineResponse> searchMedicinesByName(String name);
    List<MedicineResponse> getExpiredMedicines();
    List<MedicineResponse> getLowStockMedicines(Integer threshold);
    MedicineResponse updateStock(UUID id, Integer quantity);

}
