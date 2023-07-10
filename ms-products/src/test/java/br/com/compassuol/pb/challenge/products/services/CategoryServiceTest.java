package br.com.compassuol.pb.challenge.products.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.compassuol.pb.challenge.products.dtos.CategoryDTO;
import br.com.compassuol.pb.challenge.products.entities.Category;
import br.com.compassuol.pb.challenge.products.repositories.CategoryRepository;

@SpringBootTest
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Test Category");

        Category category = new Category();
        category.setName(categoryDTO.getName());

        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(categoryDTO);

        assertNotNull(createdCategory);
        assertEquals(categoryDTO.getName(), createdCategory.getName());
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Category retrievedCategory = categoryService.getCategoryById(categoryId);

        assertNotNull(retrievedCategory);
        assertEquals(categoryId, retrievedCategory.getId());
        assertEquals(category.getName(), retrievedCategory.getName());
    }

    @Test
    public void testGetAllCategories() {
        int page = 0;
        int linesPerPage = 10;
        String direction = "asc";
        String orderBy = "name";

        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Category 1"));
        categoryList.add(new Category("Category 2"));

        Page<Category> categoryPage = new PageImpl<>(categoryList);

        Mockito.when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);

        Page<Category> retrievedCategories = categoryService.getAllCategories(page, linesPerPage, direction, orderBy);

        assertNotNull(retrievedCategories);
        assertEquals(2, retrievedCategories.getTotalElements());
        assertEquals(categoryList.size(), retrievedCategories.getContent().size());
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;

        categoryService.deleteCategory(categoryId);

        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(categoryId);
    }

    @Test
    public void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Updated Category");

        Category existingCategory = new Category();
        existingCategory.setId(categoryId);
        existingCategory.setName("Existing Category");

        Category updatedCategory = new Category();
        updatedCategory.setId(categoryId);
        updatedCategory.setName(categoryDTO.getName());

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(updatedCategory);

        Category resultCategory = categoryService.updateCategory(categoryId, categoryDTO);

        assertNotNull(resultCategory);
        assertEquals(categoryId, resultCategory.getId());
        assertEquals(categoryDTO.getName(), resultCategory.getName());
    }
    
}