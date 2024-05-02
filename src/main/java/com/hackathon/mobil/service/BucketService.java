package com.hackathon.mobil.service;

import com.hackathon.mobil.dao.BucketRepo;
import com.hackathon.mobil.entity.Bucket;

import com.hackathon.mobil.entity.Product;
import com.hackathon.mobil.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BucketService  {
    private final BucketRepo repo;
    private final UserService userService;
    private final ProductService productService;

    public BucketService(BucketRepo repo, UserService userService, ProductService productService) {
        this.repo = repo;
        this.userService = userService;
        this.productService = productService;
    }
    public void  delete(int  id){
        Bucket product= repo.findById(id).orElseThrow();
        repo.delete(product);
    }
    public Optional<Bucket> find(int id){
        return repo.findById(id);
    }
    public void addProductToBucket(int userId, int productId) {
        Optional<User> user = userService.find(userId);
        Optional<Product> product = productService.find(productId);
        Bucket newBucket = Bucket.builder()
                .user(user.orElseThrow())
                .product(product.orElseThrow())
                .state(true)
                .build();
        repo.save(newBucket);
    }

    public void sepetTamamla(int bucketId){
        Optional<Bucket> bucketOptional = repo.findById(bucketId);
        if (bucketOptional.isPresent()) {
            Bucket bucket = bucketOptional.get();
            bucket.setState(false);
            repo.save(bucket);
        }
    }

}
