package com.pcbuild.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pcbuild.model.OfflineOrder;

public interface OfflineOrderRepository extends MongoRepository<OfflineOrder, String> {
}
