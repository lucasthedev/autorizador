package com.authorizer.service.infrastructure.persistence.mcc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MccRepository extends JpaRepository<MccJpaEntity, Integer> {
}
