package com.authorizer.service.usecase.account;

import com.authorizer.service.domain.account.AccountGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class CreateAccountUseCaseTest {
    @InjectMocks
    private CreateAccountUseCase useCase;
    @Mock
    private AccountGateway accountGateway;

    @BeforeEach
    public void cleanUp(){
        Mockito.reset(accountGateway);
    }

    @Test
    public void givenValidParams_whenCallAccountUseCase_thenReturnNewAccountCreated(){
        final var documentNumber = "123456789";
        final var availableFoodCreditLimit = BigDecimal.valueOf(201.00);
        final var availableMealCreditLimit = BigDecimal.valueOf(202.00);
        final var availableCashCreditLimit = BigDecimal.valueOf(203.00);

        Mockito.when(accountGateway.create(Mockito.any())).thenAnswer(returnsFirstArg());

        final var command = CreateAccountCommand.with(documentNumber,availableFoodCreditLimit,
                availableMealCreditLimit,availableCashCreditLimit);

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(availableFoodCreditLimit, output.availableFoodCreditLimit());
        Assertions.assertEquals(availableMealCreditLimit, output.availableMealCreditLimit());
        Assertions.assertEquals(availableCashCreditLimit, output.availableCashCreditLimit());
        Mockito.verify(accountGateway, Mockito.times(1)).create(Mockito.any());
    }
}
