package com.authorizer.service.api.controllers.request;

import java.math.BigDecimal;

public record CreateAccountRequest(String documentNumber,
                                   BigDecimal availableFoodCreditLimit,
                                   BigDecimal availableMealCreditLimit,
                                   BigDecimal availableCashCreditLimit) {
}
