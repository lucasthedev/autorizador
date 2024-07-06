package com.authorizer.service.infrastructure.persistence.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountJpaEntity,String> {
}
