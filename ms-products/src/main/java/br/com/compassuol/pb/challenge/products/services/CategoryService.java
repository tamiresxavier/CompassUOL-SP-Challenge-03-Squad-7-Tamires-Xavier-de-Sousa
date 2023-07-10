package br.com.compassuol.pb.challenge.products.services;

import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.products.dtos.CategoryDTO;
import br.com.compassuol.pb.challenge.products.entities.Category;
import br.com.compassuol.pb.challenge.products.repositories.CategoryRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        
        return categoryRepository.save(category);
    }
    
    public Category getCategoryById(Long id) {
    	Optional<Category> optionalCategory = categoryRepository.findById(id);
    	if(optionalCategory.isPresent()) {
    		return optionalCategory.get();
    	}
    	throw new RuntimeException("Category not found with id " + id);
    }
    
    public Page<Category> getAllCategories(int page, int linesPerPage, String direction, String orderBy) {
        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return categoryRepository.findAll(pageable);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
    	Category existingCategory = getCategoryById(id);
        if(existingCategory == null) {
        	throw new IllegalArgumentException("Category not found with id " + id);
        }
        
        existingCategory.setName(categoryDTO.getName());
        
        return categoryRepository.save(existingCategory);
    }
    
}
