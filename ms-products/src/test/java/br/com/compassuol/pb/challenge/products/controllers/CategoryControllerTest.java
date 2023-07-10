package br.com.compassuol.pb.challenge.products.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.compassuol.pb.challenge.products.dtos.CategoryDTO;
import br.com.compassuol.pb.challenge.products.entities.Category;
import br.com.compassuol.pb.challenge.products.services.CategoryService;

@SpringBootTest
public class CategoryControllerTest {

	@InjectMocks
	private CategoryController categoryController;
	
	@Mock
	private CategoryService categoryService;
	
	private Category category;
	private CategoryDTO categoryDTO;
	private Page<Category> categoryPage;
	private List<Category> categoryList;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		category = new Category("Test Category");
		categoryDTO = new CategoryDTO("Test Category");
		
		categoryList = new ArrayList<>();
		categoryList.add(category);
		categoryPage = new PageImpl<>(categoryList);
	}
	
	@Test
	void testCreateCategory() {
		when(categoryService.createCategory(categoryDTO)).thenReturn(category);
		
		ResponseEntity<Category> response = categoryController.createCategory(categoryDTO);
		
		assertEquals(category, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testGetCategoryById() {
		Long categoryId = 1L;
		when(categoryService.getCategoryById(categoryId)).thenReturn(category);
		
		ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);
		
		assertEquals(category, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testGetAllCategories() {
		int page = 0;
		int linesPerPage = 10;
		String direction = "ASC";
		String orderBy = "id";
		
		when(categoryService.getAllCategories(page, linesPerPage, direction, orderBy)).thenReturn(categoryPage);
		
		ResponseEntity<Page<Category>> response = categoryController.getAllCategories(page, linesPerPage, direction, orderBy);
		
		assertEquals(categoryPage, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testDeleteCategory() {
		Long categoryId = 1L;
		
		ResponseEntity<Void> response = categoryController.deleteCategory(categoryId);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	void testUpdateCategory() {
		Long categoryId = 1L;
		
		when(categoryService.updateCategory(categoryId, categoryDTO)).thenReturn(category);
		
		ResponseEntity<Category> response = categoryController.updateCategory(categoryId, categoryDTO);
		
		assertEquals(category, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}