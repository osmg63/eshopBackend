package com.hackathon.mobil.service;

import com.hackathon.mobil.dao.ProductRepo;
import com.hackathon.mobil.entity.Product;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public Product  insert(Product product){
        return repo.save(product);
    }
    public void  delete(int  id){
        Product product= repo.findById(id).orElseThrow();
        repo.delete(product);
    }
    public Optional<Product> find(int id){
        return repo.findById(id);
    }


}
