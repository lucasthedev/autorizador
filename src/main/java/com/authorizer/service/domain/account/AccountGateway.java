package com.authorizer.service.domain.account;

import java.util.Optional;

public interface AccountGateway {
    Account create(Account account);

    Optional<Account> findAccountById(String id);

    void update(Account account);
}
