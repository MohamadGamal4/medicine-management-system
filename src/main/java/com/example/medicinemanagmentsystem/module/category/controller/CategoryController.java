package com.example.medicinemanagmentsystem.module.category.controller;

import com.example.medicinemanagmentsystem.module.category.dto.request.CategoryRequest;
import com.example.medicinemanagmentsystem.module.category.dto.response.CategoryResponse;
import com.example.medicinemanagmentsystem.module.category.service.CategoryService;
import com.example.medicinemanagmentsystem.module.medicine.dto.response.MedicineResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
   public ResponseEntity<CategoryResponse> createCategory (@Valid @RequestBody CategoryRequest request){
      CategoryResponse categoryResponse = categoryService.createCategory(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @GetMapping
  public ResponseEntity<List<CategoryResponse>> getAllCategories (){
      List<CategoryResponse> categoryResponse= categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable UUID id, @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/{id}/medicines")
    public ResponseEntity<List<MedicineResponse>> getMedicinesByCategory(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getMedicinesByCategory(id));
    }
}
