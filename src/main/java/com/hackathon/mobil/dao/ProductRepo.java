package com.hackathon.mobil.dao;

import com.hackathon.mobil.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Integer> {

}
