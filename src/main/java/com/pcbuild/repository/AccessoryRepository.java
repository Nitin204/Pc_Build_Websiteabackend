package com.pcbuild.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pcbuild.model.Accessory;

public interface AccessoryRepository extends MongoRepository<Accessory, String> {
    List<Accessory> findByCategory(String category);
}
