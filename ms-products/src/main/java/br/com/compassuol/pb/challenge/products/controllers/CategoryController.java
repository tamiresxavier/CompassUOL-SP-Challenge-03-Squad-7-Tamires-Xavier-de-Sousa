package br.com.compassuol.pb.challenge.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassuol.pb.challenge.products.dtos.CategoryDTO;
import br.com.compassuol.pb.challenge.products.entities.Category;
import br.com.compassuol.pb.challenge.products.services.CategoryService;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
    	Category category = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok().body(category);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    	Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }
    
    @GetMapping
    public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int linesPerPage,
                                                        @RequestParam(defaultValue = "ASC") String direction,
                                                        @RequestParam(defaultValue = "id") String orderBy) {
        Page<Category> categories = categoryService.getAllCategories(page, linesPerPage, direction, orderBy);
        return ResponseEntity.ok().body(categories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    	categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
    	Category category = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok().body(category);
    }
    
}