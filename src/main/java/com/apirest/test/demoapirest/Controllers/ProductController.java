package com.apirest.test.demoapirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.test.demoapirest.Repositories.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.apirest.test.demoapirest.Entities.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable int id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
    @PostMapping
    public Product createProduct(@Valid @RequestBody Product entity) {
        return productRepository.save(entity);
    }
    
    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @Valid @RequestBody Product entity) {
        return productRepository.findById(id)
            .map(product -> {
                product.setName(entity.getName());
                product.setBrand(entity.getBrand());
                product.setPrice(entity.getPrice());
                return productRepository.save(product);
            })
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable int id){
        return productRepository.findById(id)
            .map(product -> {
                productRepository.delete(product);
                return "Product deleted successfully";
            })
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
