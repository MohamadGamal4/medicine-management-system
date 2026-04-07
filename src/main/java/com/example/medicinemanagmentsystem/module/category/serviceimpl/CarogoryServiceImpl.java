package com.example.medicinemanagmentsystem.module.category.serviceimpl;

import com.example.medicinemanagmentsystem.module.category.dto.request.CategoryRequest;
import com.example.medicinemanagmentsystem.module.category.dto.response.CategoryResponse;
import com.example.medicinemanagmentsystem.module.category.entity.Category;
import com.example.medicinemanagmentsystem.module.category.mapper.CategoryMapper;
import com.example.medicinemanagmentsystem.module.category.repository.CategoryRepository;
import com.example.medicinemanagmentsystem.module.category.service.CategoryService;
import com.example.medicinemanagmentsystem.module.medicine.dto.response.MedicineResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Setter
@Getter
@Service
public class CarogoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;


    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(categoryMapper::toResponse).toList();
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {
        return categoryMapper.toResponse(
                categoryRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found")));
    }

    @Override
    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        categoryMapper.toEntity(request, category);
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);

    }


    @Override
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        categoryRepository.delete(category);

    }


    @Override
    public List<MedicineResponse> getMedicinesByCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return category.getMedicines()
                .stream().map(categoryMapper::toMidicineResponse).toList();
    }


}
