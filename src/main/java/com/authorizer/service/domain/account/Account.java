package com.authorizer.service.domain.account;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Account {
    private String id;
    private String documentNumber;
    private Instant createdAt;
    private BigDecimal availableFoodCreditLimit;
    private BigDecimal availableMealCreditLimit;
    private BigDecimal availableCashCreditLimit;

    private Account(final String id,
    final String documentNumber,
    final Instant createdAt,
    final BigDecimal availableFoodCreditLimit,
    final BigDecimal availableMealCreditLimit,
    final BigDecimal availableCashCreditLimit){
        this.id = id;
        this.documentNumber = documentNumber;
        this.createdAt = createdAt;
        this.availableFoodCreditLimit = availableFoodCreditLimit;
        this.availableMealCreditLimit = availableMealCreditLimit;
        this.availableCashCreditLimit = availableCashCreditLimit;
    }

    public static Account newAccount(final String documentNumber,
                                     final BigDecimal availableFoodCreditLimit,
                                     final BigDecimal availableMealCreditLimit,
                                     final BigDecimal availableCashCreditLimit){
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Account(id,documentNumber,now,availableFoodCreditLimit, availableMealCreditLimit, availableCashCreditLimit);
    }

    public static Account with(final String id,
                           final String documentNumber,
                           final Instant createdAt,
                           final BigDecimal availableFoodCreditLimit,
                           final BigDecimal availableMealCreditLimit,
                           final BigDecimal availableCashCreditLimit){
        return new Account(id,documentNumber, createdAt, availableFoodCreditLimit, availableMealCreditLimit, availableCashCreditLimit);
    }

    public String getId() {
        return id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getAvailableFoodCreditLimit() {
        return availableFoodCreditLimit;
    }

    public BigDecimal getAvailableCashCreditLimit() {
        return availableCashCreditLimit;
    }

    public BigDecimal getAvailableMealCreditLimit() {
        return availableMealCreditLimit;
    }

    public void subtractFromCashCreditLimit(BigDecimal amount){
        this.availableCashCreditLimit = availableCashCreditLimit.subtract(amount);
    }

    public void subtractFromFoodCreditLimit(BigDecimal amount){
        this.availableFoodCreditLimit = availableFoodCreditLimit.subtract(amount);
    }

    public void subtractFromMealCreditLimit(BigDecimal amount){
        this.availableMealCreditLimit = availableMealCreditLimit.subtract(amount);
    }
}
