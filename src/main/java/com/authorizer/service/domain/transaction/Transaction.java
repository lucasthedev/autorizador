package com.authorizer.service.domain.transaction;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    private String id;
    private String documentNumber;
    private Instant createdAt;
    private BigDecimal availableCreditLimit;
}
