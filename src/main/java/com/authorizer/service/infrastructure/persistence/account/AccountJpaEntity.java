package com.authorizer.service.infrastructure.persistence.account;

import com.authorizer.service.domain.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "account")
public class AccountJpaEntity {
    @Id
    private String id;
    @Column(name = "document_number", length = 50, nullable = false)
    private String documentNumber;
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP(6)")
    private Instant createdAt;
    @Column(name = "available_food_credit", nullable = false)
    private BigDecimal availableFoodCreditLimit;
    @Column(name = "available_meal_credit", nullable = false)
    private BigDecimal availableMealCreditLimit;
    @Column(name = "available_cash_credit", nullable = false)
    private BigDecimal availableCashCreditLimit;

    public AccountJpaEntity(){}

    public AccountJpaEntity(String id, String documentNumber, Instant createdAt, BigDecimal availableFoodCreditLimit, BigDecimal availableMealCreditLimit, BigDecimal availableCashCreditLimit) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.createdAt = createdAt;
        this.availableFoodCreditLimit = availableFoodCreditLimit;
        this.availableMealCreditLimit = availableMealCreditLimit;
        this.availableCashCreditLimit = availableCashCreditLimit;
    }

    public static AccountJpaEntity from(final Account account){
        return new AccountJpaEntity(account.getId(),
                account.getDocumentNumber(),
                account.getCreatedAt(),
                account.getAvailableFoodCreditLimit(),
                account.getAvailableMealCreditLimit(),
                account.getAvailableCashCreditLimit()
        );
    }

    public Account toDomain(){
        return Account.with(getId(),
                getDocumentNumber(),
                getCreatedAt(),
                getAvailableFoodCreditLimit(),
                getAvailableMealCreditLimit(),
                getAvailableCashCreditLimit());
    }

    public static Account with(final AccountJpaEntity entity){
        return Account.with(entity.getId(),
                entity.getDocumentNumber(),
                entity.getCreatedAt(),
                entity.getAvailableFoodCreditLimit(),
                entity.getAvailableMealCreditLimit(),
                entity.getAvailableCashCreditLimit()
        );
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

    public BigDecimal getAvailableMealCreditLimit() {
        return availableMealCreditLimit;
    }

    public BigDecimal getAvailableCashCreditLimit() {
        return availableCashCreditLimit;
    }
}
