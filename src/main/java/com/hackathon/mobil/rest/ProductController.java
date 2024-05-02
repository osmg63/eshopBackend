package com.hackathon.mobil.rest;

import com.hackathon.mobil.entity.Product;
import com.hackathon.mobil.entity.User;
import com.hackathon.mobil.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/PUBLIC/product")
    public Product updateProduct(@RequestBody Product product){return productService.insert(product);}


}
