package com.example.medicinemanagmentsystem.module.medicine.mapper;

import com.example.medicinemanagmentsystem.module.category.mapper.CategoryMapper;
import com.example.medicinemanagmentsystem.module.medicine.dto.request.MedicineRequest;
import com.example.medicinemanagmentsystem.module.medicine.dto.response.MedicineResponse;
import com.example.medicinemanagmentsystem.module.medicine.entity.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {CategoryMapper.class})
public interface MedicineMapper {
    @Mapping(target = "category", ignore = true)
    Medicine toEntity(MedicineRequest request);

    MedicineResponse toResponse(Medicine medicine);

    @Mapping(target = "category", ignore = true)
    void updateEntityFromRequest(MedicineRequest request, @MappingTarget Medicine medicine);

}
