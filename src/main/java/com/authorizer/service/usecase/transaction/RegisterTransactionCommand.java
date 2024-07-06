package com.authorizer.service.usecase.transaction;

import java.math.BigDecimal;

public record RegisterTransactionCommand(String accountId,
                                         BigDecimal amount,
                                         String mcc,
                                         String merchant) {
    public static RegisterTransactionCommand with(final String accountId,
                                                  final BigDecimal amount,
                                                  final String mcc,
                                                  final String merchant){
        return new RegisterTransactionCommand(accountId,
                amount,
                mcc,
                merchant
        );
    }
}
