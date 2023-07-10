package br.com.compassuol.pb.challenge.products.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.products.entities.Product;
import br.com.compassuol.pb.challenge.products.repositories.ProductRepository;
import br.com.compassuol.pb.challenge.products.dtos.ProductDTO;
import br.com.compassuol.pb.challenge.products.entities.Category;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryService categoryService;

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setDate(productDTO.getDate());
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setImgUrl(productDTO.getImgUrl());
        product.setPrice(productDTO.getPrice());
        
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : productDTO.getCategoryIds()) {
            Category category = categoryService.getCategoryById(categoryId);
            categories.add(category);
        }
        
        product.setCategories(categories);
        
        return productRepository.save(product);
    }
    
    public Product getProductById(Long id) {
    	Optional<Product> optionalProduct = productRepository.findById(id);
    	if(optionalProduct.isPresent()) {
    		return optionalProduct.get();
    	}
    	throw new RuntimeException("Product not found with id " + id);
    }
    
    public Page<Product> getAllProducts(int page, int linesPerPage, String direction, String orderBy) {
        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return productRepository.findAll(pageable);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = getProductById(id);
        if(existingProduct == null) {
        	throw new IllegalArgumentException("Product not found with id " + id);
        }
        
        existingProduct.setDate(productDTO.getDate());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setName(productDTO.getName());
        existingProduct.setImgUrl(productDTO.getImgUrl());
        existingProduct.setPrice(productDTO.getPrice());
        
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : productDTO.getCategoryIds()) {
            Category category = categoryService.getCategoryById(categoryId);
            categories.add(category);
        }
        
        existingProduct.setCategories(categories);
        
        return productRepository.save(existingProduct);
    }
    
}