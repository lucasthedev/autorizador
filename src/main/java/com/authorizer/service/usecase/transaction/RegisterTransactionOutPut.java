package com.authorizer.service.usecase.transaction;

import com.authorizer.service.domain.transaction.Transaction;

public record RegisterTransactionOutPut(String code) {
    public static RegisterTransactionOutPut from(final String code){
        return new RegisterTransactionOutPut(code);
    }
}
