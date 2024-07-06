package com.authorizer.service.infrastructure.persistence.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionJpaEntity, String> {
}
