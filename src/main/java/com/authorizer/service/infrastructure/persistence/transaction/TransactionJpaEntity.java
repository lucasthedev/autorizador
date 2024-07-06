package com.authorizer.service.infrastructure.persistence.transaction;

import com.authorizer.service.domain.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class TransactionJpaEntity {
    @Id
    private String id;
    @Column(name = "account_id", nullable = false)
    private String accountId;
    @Column(name = "mcc_code", nullable = false)
    private String mccCode;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "merchant", nullable = false, length = 200)
    private String merchant;
    @Column(name = "status_code", nullable = false, length = 6)
    private String statusCode;
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP(6)")
    private Instant createdAt;

    public TransactionJpaEntity(){}

    public TransactionJpaEntity(String id, String accountId, String mccCode, BigDecimal amount,
                                String merchant, String statusCode, Instant createdAt){
        this.id = id;
        this.accountId = accountId;
        this.mccCode = mccCode;
        this.amount = amount;
        this.merchant = merchant;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }

    public static TransactionJpaEntity from(Transaction transaction){
        return new TransactionJpaEntity(transaction.getId(),
                transaction.getAccountId(),
                transaction.getMcc(),
                transaction.getAmount(),
                transaction.getMerchant(),
                transaction.getStatusCode(),
                transaction.getCreatedAt()
        );
    }

    public Transaction toDomain(){
        return Transaction.with(
                getId(),
                getAccountId(),
                getMccCode(),
                getAmount(),
                getCreatedAt(),
                getMerchant(),
                getStatusCode()
        );
    }

    public static Transaction with(final TransactionJpaEntity entity){
        return Transaction.with(
                entity.getId(),
                entity.getAccountId(),
                entity.getMccCode(),
                entity.getAmount(),
                entity.getCreatedAt(),
                entity.getMerchant(),
                entity.getStatusCode());
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMerchant() {
        return merchant;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getMccCode() {
        return mccCode;
    }
}
