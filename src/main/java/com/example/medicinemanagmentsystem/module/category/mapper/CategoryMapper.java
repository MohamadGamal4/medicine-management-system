package com.example.medicinemanagmentsystem.module.category.mapper;


import com.example.medicinemanagmentsystem.module.category.dto.request.CategoryRequest;
import com.example.medicinemanagmentsystem.module.category.dto.response.CategoryResponse;
import com.example.medicinemanagmentsystem.module.category.entity.Category;
import com.example.medicinemanagmentsystem.module.medicine.mapper.MedicineMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MedicineMapper.class})
public interface CategoryMapper {
    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);

    void toEntity(CategoryRequest request, @MappingTarget Category category);
}