package com.example.medicinemanagmentsystem.module.medicine.serviceimpl;

import com.example.medicinemanagmentsystem.module.category.entity.Category;
import com.example.medicinemanagmentsystem.module.category.repository.CategoryRepository;
import com.example.medicinemanagmentsystem.module.medicine.dto.request.MedicineRequest;
import com.example.medicinemanagmentsystem.module.medicine.dto.response.MedicineResponse;
import com.example.medicinemanagmentsystem.module.medicine.entity.Medicine;
import com.example.medicinemanagmentsystem.module.medicine.mapper.MedicineMapper;
import com.example.medicinemanagmentsystem.module.medicine.repository.MedicineRepository;
import com.example.medicinemanagmentsystem.module.medicine.service.MedicineService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public MedicineResponse createMedicine(MedicineRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        Medicine medicine = medicineMapper.toEntity(request);
        medicine.setCategory(category);

        return medicineMapper.toResponse(medicineRepository.save(medicine));
    }

    @Override
    public List<MedicineResponse> getAllMedicines() {

        return medicineRepository.findAll().stream().map(medicineMapper::toResponse).toList();

    }


    @Override
    public MedicineResponse getMedicineById(UUID id) {

        return medicineMapper.toResponse(medicineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicine not found")));


    }


    @Override
    @Transactional
    public MedicineResponse updateMedicine(UUID id, MedicineRequest request) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        medicineMapper.updateEntityFromRequest(request, medicine);
        medicine.setCategory(category);
        return medicineMapper.toResponse(medicineRepository.save(medicine));
    }

    @Override
    @Transactional
    public void deleteMedicine(UUID id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
        medicineRepository.delete(medicine);
    }


    @Override
    public List<MedicineResponse> searchMedicinesByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name).stream().map(medicineMapper::toResponse).toList();
    }


    @Override
    public List<MedicineResponse> getExpiredMedicines() {
        return medicineRepository.findByExpiryDateBefore(LocalDate.now())
                .stream().map(medicineMapper::toResponse).toList();
    }


    @Override
    public List<MedicineResponse> getLowStockMedicines(Integer threshold) {
        return medicineRepository.findByQuantityInStockLessThanEqual(threshold)
                .stream().map(medicineMapper::toResponse).toList();
    }


    @Override
    @Transactional
    public MedicineResponse updateStock(UUID id, Integer quantity) {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
        medicine.setQuantityInStock(quantity);

        return medicineMapper.toResponse(medicineRepository.save(medicine));
    }

}
