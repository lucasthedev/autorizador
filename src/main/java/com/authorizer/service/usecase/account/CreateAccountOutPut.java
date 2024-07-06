package com.authorizer.service.usecase.account;

import com.authorizer.service.domain.account.Account;

import java.math.BigDecimal;

public record CreateAccountOutPut(String id, BigDecimal availableFoodCreditLimit,
                                  BigDecimal availableMealCreditLimit,
                                  BigDecimal availableCashCreditLimit) {
    public static CreateAccountOutPut from(final Account account){
        return new CreateAccountOutPut(account.getId(),
                account.getAvailableFoodCreditLimit(),
                account.getAvailableMealCreditLimit(),
                account.getAvailableCashCreditLimit()
        );
    }
}
