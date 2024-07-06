package com.authorizer.service.domain.mcc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MccTest {
    @Test
    public void givenValidParams_whenCallCreateMcc_shouldCreateMcc(){
        final var expectedId = Integer.valueOf(1);
        final var expectedMccCode = "5812";
        final var expectedDescription = "MEAL";

        final var actualMcc = Mcc.with(expectedId,expectedMccCode,expectedDescription);

        Assertions.assertEquals(expectedId, actualMcc.getId());
        Assertions.assertEquals(expectedMccCode, actualMcc.getMccCode());
        Assertions.assertEquals(expectedDescription, actualMcc.getDescription());
    }
}
