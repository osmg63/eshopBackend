package com.hackathon.mobil.rest;
import com.hackathon.mobil.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BucketController {

    private final BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("/USER/bucket/add")
    public ResponseEntity<String> addProductToBucket(@RequestParam("userId") int userId, @RequestParam("productId") int productId) {
        try {
            bucketService.addProductToBucket(userId, productId);
            return ResponseEntity.ok("Product added to bucket successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product to bucket: " + e.getMessage());
        }
    }
    @PostMapping ("/USER/bucket/verify")
    public ResponseEntity<String> verifyBucket(@RequestParam("bucketId") int bucketId){
        try{
            bucketService.sepetTamamla(bucketId);
            return ResponseEntity.ok("Bucket veriyfy succesfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to veriyfy to bucket: " + e.getMessage());
        }
    }
}
