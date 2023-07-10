package br.com.compassuol.pb.challenge.products.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import br.com.compassuol.pb.challenge.products.entities.Product;
import br.com.compassuol.pb.challenge.products.repositories.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));

        // Act
        Product result = productService.getProductById(productId);

        // Assert
        assertNotNull(result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        int page = 0;
        int linesPerPage = 10;
        String direction = "ASC";
        String orderBy = "name";

        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        when(productRepository.findAll(pageable)).thenReturn(Page.empty());

        // Act
        Page<Product> result = productService.getAllProducts(page, linesPerPage, direction, orderBy);

        // Assert
        assertNotNull(result);
        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }
    
}