package br.com.compassuol.pb.challenge.products.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import br.com.compassuol.pb.challenge.products.dtos.ProductDTO;
import br.com.compassuol.pb.challenge.products.entities.Product;
import br.com.compassuol.pb.challenge.products.services.ProductService;

@SpringBootTest
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO("2021-01-01", "Test Product", "A description", "image.jpg", 9.99f);
        Product product = new Product(productDTO.getDate(), productDTO.getDescription(), productDTO.getName(), productDTO.getImgUrl(), productDTO.getPrice());
        when(productService.createProduct(productDTO)).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.createProduct(productDTO);

        assertNotNull(responseEntity.getBody());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;
        Product product = new Product("2021-01-01", "Test Product", "A description", "image.jpg", 9.99f);
        when(productService.getProductById(productId)).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.getProductById(productId);

        assertNotNull(responseEntity.getBody());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    public void testGetAllProducts() {
        int page = 0;
        int linesPerPage = 10;
        String direction = "ASC";
        String orderBy = "id";

        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("2021-01-01", "Test Product 1", "Description 1", "image1.jpg", 9.99f));
        productList.add(new Product("2021-01-02", "Test Product 2", "Description 2", "image2.jpg", 14.99f));
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productService.getAllProducts(page, linesPerPage, direction, orderBy)).thenReturn(productPage);

        ResponseEntity<Page<Product>> responseEntity = productController.getAllProducts(page, linesPerPage, direction, orderBy);

        assertNotNull(responseEntity.getBody());
        assertEquals(productPage, responseEntity.getBody());
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        productService.deleteProduct(productId);

        ResponseEntity<Void> responseEntity = productController.deleteProduct(productId);

        assertEquals(ResponseEntity.noContent().build(), responseEntity);
    }

    @Test
    public void testUpdateProduct() {
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO("2021-01-01", "Updated Product", "Updated description", "updated_image.jpg", 19.99f);
        Product product = new Product(productDTO.getDate(), productDTO.getDescription(), productDTO.getName(), productDTO.getImgUrl(), productDTO.getPrice());
        when(productService.updateProduct(productId, productDTO)).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.updateProduct(productId, productDTO);

        assertNotNull(responseEntity.getBody());
        assertEquals(product, responseEntity.getBody());
    }

}