package com.elkabani.mydb.services;

import com.elkabani.mydb.entities.Category;
import com.elkabani.mydb.entities.Product;
import com.elkabani.mydb.repositories.CategoryRepository;
import com.elkabani.mydb.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Category createProductWithNewCategory()
    {
        var category = Category.builder()
                .name("Electronics")
                .build();

        var product = Product.builder()
                .name("Camera")
                .price(new BigDecimal("499.99"))
                .category(category)
                .build();

        productRepository.save(product);
        return category;
    }

    @Transactional
    public Product createProductForExistingCategory(Integer categoryId)
    {
        var category = categoryRepository.findById(categoryId).orElseThrow();

        var product = Product.builder()
                .name("Headphones")
                .price(new BigDecimal("79.99"))
                .category(category)
                .build();

        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Long productId)
    {
        productRepository.deleteById(productId);
    }

    public void printProducts()
    {
        var products = productRepository.findAll();
        products.forEach(p -> System.out.println(p.getName() + " - " + p.getCategory().getName()));
    }
}
