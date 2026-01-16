package com.pcbuild.repository;

import com.pcbuild.model.CareerApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CareerRepository extends MongoRepository<CareerApplication, String> {

   
}
