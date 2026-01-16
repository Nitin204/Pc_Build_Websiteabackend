package com.pcbuild.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pcbuild.model.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {

    Optional<Admin> findByUsername(String username);
}
