package com.pcbuild.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pcbuild.model.PCBuild;

public interface PCBuildRepository extends MongoRepository<PCBuild, String> {
}
