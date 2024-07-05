package com.authorizer.service.domain.account;

import java.math.BigDecimal;
import java.time.Instant;

public class Account {
    private String id;
    private String documentNumber;
    private Instant createdAt;
    private BigDecimal availableFoodCreditLimit;
    private BigDecimal availableMealCreditLimit;
    private BigDecimal availableCashCreditLimit;
}
