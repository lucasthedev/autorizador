package com.authorizer.service.usecase.account;

import com.authorizer.service.domain.account.Account;
import com.authorizer.service.domain.account.AccountGateway;
import com.authorizer.service.exceptions.DomainException;
import com.authorizer.service.usecase.UseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateAccountUseCase extends UseCase<CreateAccountCommand, CreateAccountOutPut> {
    private final AccountGateway accountGateway;

    public CreateAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public CreateAccountOutPut execute(CreateAccountCommand input){
        final var documentNumber = input.documentNumber();
        final var availableFoodCreditLimit = input.availableFoodCreditLimit();
        final var availableMealCreditLimit = input.availableMealCreditLimit();
        final var availableCashCreditLimit = input.availableCashCreditLimit();

        final var account =
                Account.newAccount(documentNumber,
                        availableFoodCreditLimit,
                        availableMealCreditLimit,
                        availableCashCreditLimit);

        final var accountCreated = accountGateway.create(account);

        return CreateAccountOutPut.from(accountCreated);
    }
}
