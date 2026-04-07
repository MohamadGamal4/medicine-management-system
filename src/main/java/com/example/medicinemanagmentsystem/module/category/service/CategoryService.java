package com.example.medicinemanagmentsystem.module.category.service;

import com.example.medicinemanagmentsystem.module.category.dto.request.CategoryRequest;
import com.example.medicinemanagmentsystem.module.category.dto.response.CategoryResponse;
import com.example.medicinemanagmentsystem.module.medicine.dto.response.MedicineResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {




    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(UUID id);

    CategoryResponse updateCategory(UUID id, CategoryRequest request);

    void deleteCategory(UUID id);
    List<MedicineResponse> getMedicinesByCategory(UUID categoryId);
}
