package com.authorizer.service.domain.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountTest {
    @Test
    public void givenValidParams_whenCallCreateAccount_shouldCreateAccountObject(){
        final var expectedDocumentNumber = "123456";
        final var expectedAvailableFoodCreditLimit = BigDecimal.valueOf(1219.45);
        final var expectedAvailableMealCreditLimit = BigDecimal.valueOf(1219.45);
        final var expectedAvailableCashCreditLimit = BigDecimal.valueOf(1219.45);

        final var actualAccount = Account.newAccount(expectedDocumentNumber,
                expectedAvailableFoodCreditLimit,
                expectedAvailableMealCreditLimit,
                expectedAvailableCashCreditLimit);

        Assertions.assertNotNull(actualAccount.getId());
        Assertions.assertEquals(expectedDocumentNumber, actualAccount.getDocumentNumber());
        Assertions.assertEquals(expectedAvailableFoodCreditLimit, actualAccount.getAvailableFoodCreditLimit());
        Assertions.assertEquals(expectedAvailableMealCreditLimit, actualAccount.getAvailableMealCreditLimit());
        Assertions.assertEquals(expectedAvailableCashCreditLimit, actualAccount.getAvailableCashCreditLimit());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
    }
}
