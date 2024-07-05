package com.authorizer.service.domain.transaction;

import com.authorizer.service.enums.MCC;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    private String id;
    private String accountId;
    private MCC mcc;
    private BigDecimal amount;
    private Instant createdAt;
}
