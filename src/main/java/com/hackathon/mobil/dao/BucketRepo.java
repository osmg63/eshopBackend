package com.hackathon.mobil.dao;

import com.hackathon.mobil.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BucketRepo extends JpaRepository<Bucket,Integer> {
}
