package com.pcbuild.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import com.pcbuild.model.OfflineOrder;

public interface OfflineOrderRepository extends MongoRepository<OfflineOrder, String> {

    @Aggregation(pipeline = {
        "{ $group: { _id: null, totalRevenue: { $sum: \"$total\" } } }"
    })
    Double getTotalOfflineRevenue();
}
