package com.authorizer.service.infrastructure;

import com.authorizer.service.domain.account.Account;
import com.authorizer.service.domain.account.AccountGateway;
import com.authorizer.service.infrastructure.persistence.account.AccountJpaEntity;
import com.authorizer.service.infrastructure.persistence.account.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountPostgresGateway implements AccountGateway {
    private final AccountRepository repository;

    public AccountPostgresGateway(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account create(Account account) {
        return repository.save(AccountJpaEntity.from(account)).toDomain();
    }

    @Override
    public Optional<Account> findAccountById(String id) {
        return Optional.of(
                AccountJpaEntity
                        .with(repository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Account with id " + id + " was not found"))));
    }

    @Override
    public void update(Account account) {
        this.repository.save(AccountJpaEntity.from(account));
    }
}
