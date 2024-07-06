package com.authorizer.service.infrastructure;

import com.authorizer.service.domain.transaction.Transaction;
import com.authorizer.service.domain.transaction.TransactionGateway;
import com.authorizer.service.infrastructure.persistence.transaction.TransactionJpaEntity;
import com.authorizer.service.infrastructure.persistence.transaction.TransactionRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionPostgresGateway implements TransactionGateway {
    private final TransactionRepository transactionRepository;

    public TransactionPostgresGateway(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(TransactionJpaEntity.from(transaction)).toDomain();
    }
}
