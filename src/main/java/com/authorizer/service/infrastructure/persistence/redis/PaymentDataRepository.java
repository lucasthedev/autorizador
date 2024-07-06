package com.authorizer.service.infrastructure.persistence.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDataRepository extends CrudRepository<PaymentData, String> {
}
