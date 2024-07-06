package com.authorizer.service.usecase.account;

import java.math.BigDecimal;

public record CreateAccountCommand(String documentNumber,
                                   BigDecimal availableFoodCreditLimit,
                                   BigDecimal availableMealCreditLimit,
                                   BigDecimal availableCashCreditLimit) {
    public static CreateAccountCommand with(final String documentNumber,
                                            final BigDecimal availableFoodCreditLimit,
                                            final BigDecimal availableMealCreditLimit,
                                            final BigDecimal availableCashCreditLimit){
        return new CreateAccountCommand(documentNumber, availableFoodCreditLimit, availableMealCreditLimit,availableCashCreditLimit);
    }
}
