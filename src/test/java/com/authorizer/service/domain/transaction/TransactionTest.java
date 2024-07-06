package com.authorizer.service.domain.transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TransactionTest {

    @Test
    public void givenValidParams_whenCallCreateTransaction_ShouldCreateTransaction(){
        final var expectedAccountId = "ac6caf52-0df3-4481-bffd-0e3dbd978e3a";
        final var expectedMccCode = "5411";
        final var expectedAmount = BigDecimal.valueOf(89.71);
        final var expectedMerchant = "PADARIA DO SEU ZÉ";
        final var expectedStatusCode = "00";

        final var transaction =
                Transaction.newTransaction(expectedAccountId,
                        expectedMccCode,
                        expectedAmount,
                        expectedMerchant,
                        expectedStatusCode);

        Assertions.assertNotNull(transaction.getId());
        Assertions.assertEquals(expectedAccountId, transaction.getAccountId());
        Assertions.assertEquals(expectedMccCode, transaction.getMcc());
        Assertions.assertEquals(expectedAmount, transaction.getAmount());
        Assertions.assertEquals(expectedMerchant, transaction.getMerchant());
        Assertions.assertEquals(expectedStatusCode, transaction.getStatusCode());
    }
}
