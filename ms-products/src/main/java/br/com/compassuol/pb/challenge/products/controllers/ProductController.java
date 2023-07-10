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

import br.com.compassuol.pb.challenge.products.dtos.ProductDTO;
import br.com.compassuol.pb.challenge.products.entities.Product;
import br.com.compassuol.pb.challenge.products.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        return ResponseEntity.ok().body(product);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }
    
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int linesPerPage,
                                                        @RequestParam(defaultValue = "ASC") String direction,
                                                        @RequestParam(defaultValue = "id") String orderBy) {
        Page<Product> products = productService.getAllProducts(page, linesPerPage, direction, orderBy);
        return ResponseEntity.ok().body(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok().body(product);
    }
}