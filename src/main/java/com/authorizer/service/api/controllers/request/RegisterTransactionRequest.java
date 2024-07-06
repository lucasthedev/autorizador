package com.authorizer.service.api.controllers.request;

import java.math.BigDecimal;

public record RegisterTransactionRequest(String accountId,
                                         BigDecimal amount,
                                         String mcc,
                                         String merchant) {
}
