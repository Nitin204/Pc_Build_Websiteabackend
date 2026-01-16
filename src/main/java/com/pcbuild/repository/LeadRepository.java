package com.pcbuild.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcbuild.model.Lead;

public interface LeadRepository extends MongoRepository<Lead, String> {
}
