package com.authorizer.service.domain.transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Transaction {
    private String id;
    private String accountId;
    private String mcc;
    private BigDecimal amount;
    private Instant createdAt;
    private String merchant;
    private String statusCode;

    private Transaction(final String id,
            final String accountId,
            final String mcc,
            final BigDecimal amount,
            final Instant createdAt,
            final String merchant,
            final String statusCode){
        this.id = id;
        this.accountId = accountId;
        this.mcc = mcc;
        this.amount = amount;
        this.createdAt = createdAt;
        this.merchant = merchant;
        this.statusCode = statusCode;
    }

    public static Transaction newTransaction(final String accountId,
                                             final String mcc,
                                             final BigDecimal amount,
                                             final String merchant,
                                             final String statusCode){
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Transaction(id,accountId,mcc,amount,now, merchant, statusCode);
    }

    public static Transaction with(final String id,
                                   final String accountId,
                                   final String mcc,
                                   final BigDecimal amount,
                                   final Instant createdAt,
                                   final String merchant,
                                   final String statusCode){
        return new Transaction(id,accountId,mcc,amount,createdAt, merchant, statusCode);
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getMcc() {
        return mcc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getMerchant(){
        return this.merchant;
    }

    public String getStatusCode(){
        return this.statusCode;
    }
}
