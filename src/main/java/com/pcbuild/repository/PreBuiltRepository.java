package com.pcbuild.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pcbuild.model.PreBuilt;

public interface PreBuiltRepository extends MongoRepository<PreBuilt, String> {
    List<PreBuilt> findByCategory(String category);
}
