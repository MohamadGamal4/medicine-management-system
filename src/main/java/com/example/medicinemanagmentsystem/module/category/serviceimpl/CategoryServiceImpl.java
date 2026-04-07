package com.example.medicinemanagmentsystem.module.category.serviceimpl;

import com.example.medicinemanagmentsystem.module.category.dto.request.CategoryRequest;
import com.example.medicinemanagmentsystem.module.category.dto.response.CategoryResponse;
import com.example.medicinemanagmentsystem.module.category.entity.Category;
import com.example.medicinemanagmentsystem.module.category.mapper.CategoryMapper;
import com.example.medicinemanagmentsystem.module.category.repository.CategoryRepository;
import com.example.medicinemanagmentsystem.module.category.service.CategoryService;
import com.example.medicinemanagmentsystem.module.medicine.dto.response.MedicineResponse;
import com.example.medicinemanagmentsystem.module.medicine.mapper.MedicineMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final MedicineMapper medicineMapper;


    @Override
    @Transactional
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
    @Transactional
    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        categoryMapper.toEntity(request, category);
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);

    }


    @Override
    @Transactional
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
                .stream().map(medicineMapper::toResponse).toList();
    }


}
