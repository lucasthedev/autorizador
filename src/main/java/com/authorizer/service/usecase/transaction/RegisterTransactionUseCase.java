package com.authorizer.service.usecase.transaction;

import com.authorizer.service.domain.transaction.Transaction;
import com.authorizer.service.domain.transaction.TransactionGateway;
import com.authorizer.service.exceptions.DomainException;
import com.authorizer.service.usecase.UseCase;
import org.springframework.stereotype.Service;

@Service
public class RegisterTransactionUseCase extends UseCase<RegisterTransactionCommand, RegisterTransactionOutPut> {
    private final TransactionGateway transactionGateway;

    private final String SUCCESS = "00";
    private final String REJECTED = "51";
    private final String ERROR = "07";

    public RegisterTransactionUseCase(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public RegisterTransactionOutPut execute(RegisterTransactionCommand input) throws DomainException {
        final var accountId = input.accountId();
        final var amount = input.amount();
        final var mcc = input.mcc();
        final var merchant = input.merchant();

        final var transaction = Transaction.newTransaction(accountId,mcc,amount, merchant, SUCCESS);

        transactionGateway.create(transaction);

        return RegisterTransactionOutPut.from(SUCCESS);
    }
}
