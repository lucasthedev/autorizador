package com.authorizer.service.usecase.transaction;

import com.authorizer.service.domain.account.Account;
import com.authorizer.service.domain.account.AccountGateway;
import com.authorizer.service.domain.mcc.Mcc;
import com.authorizer.service.domain.mcc.MccGateway;
import com.authorizer.service.domain.transaction.TransactionGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RegisterTransactionUseCaseTest {
    @InjectMocks
    private RegisterTransactionUseCase useCase;
    @Mock
    private TransactionGateway transactionGateway;
    @Mock
    private MccGateway mccGateway;
    @Mock
    private AccountGateway accountGateway;

    @Test
    public void givenValidFoodMcc_whenCallRegisterTransactionUseCase_ShouldReturnSuccessTransaction(){
        final var command = RegisterTransactionCommand.with("123456", BigDecimal.valueOf(49.85), "5411", "PADARIA DO ZE               SAO PAULO BR");
        Mockito.when(mccGateway.findAllMcc()).thenReturn(getMccList());
        Mockito.when(accountGateway.findAccountById(Mockito.any())).thenReturn(Optional.of(getAccount()));

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output.code());
        Assertions.assertEquals("00", output.code());

        Mockito.verify(transactionGateway, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void givenInvalidAmount_whenCallRegisterTransactionUseCase_ShouldReturnSuccessTransaction(){
        final var command = RegisterTransactionCommand.with("123456", BigDecimal.valueOf(1249.85), "5411", "PADARIA DO ZE               SAO PAULO BR");
        Mockito.when(mccGateway.findAllMcc()).thenReturn(getMccList());
        Mockito.when(accountGateway.findAccountById(Mockito.any())).thenReturn(Optional.of(getAccount()));

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output.code());
        Assertions.assertEquals("51", output.code());

        Mockito.verify(transactionGateway, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void givenValidAmountForCashCredit_whenCallRegisterTransactionUseCase_ShouldReturnSuccessTransaction(){
        final var command = RegisterTransactionCommand.with("123456", BigDecimal.valueOf(420), "5411", "PADARIA DO ZE               SAO PAULO BR");
        Mockito.when(mccGateway.findAllMcc()).thenReturn(getMccList());
        Mockito.when(accountGateway.findAccountById(Mockito.any())).thenReturn(Optional.of(getAccount()));

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output.code());
        Assertions.assertEquals("00", output.code());

        Mockito.verify(transactionGateway, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void givenValidMealMcc_whenCallRegisterTransactionUseCase_ShouldReturnSuccessTransaction(){
        final var command = RegisterTransactionCommand.with("123456", BigDecimal.valueOf(49.85), "5811", "PADARIA DO ZE               SAO PAULO BR");
        Mockito.when(mccGateway.findAllMcc()).thenReturn(getMccList());
        Mockito.when(accountGateway.findAccountById(Mockito.any())).thenReturn(Optional.of(getAccount()));

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output.code());
        Assertions.assertEquals("00", output.code());

        Mockito.verify(transactionGateway, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void givenInvalidMccAndValidMerchantName_whenCallRegisterTransactionUseCase_ShouldReturnSuccessTransaction(){
        final var command = RegisterTransactionCommand.with("123456", BigDecimal.valueOf(49.85), "7831", "PADARIA DO ZE               SAO PAULO BR");
        Mockito.when(mccGateway.findAllMcc()).thenReturn(getMccList());
        Mockito.when(accountGateway.findAccountById(Mockito.any())).thenReturn(Optional.of(getAccount()));

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output.code());
        Assertions.assertEquals("00", output.code());

        Mockito.verify(transactionGateway, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void givenInvalidMccAndInvalidMerchantName_whenCallRegisterTransactionUseCase_ShouldReturnSuccessTransaction(){
        final var command = RegisterTransactionCommand.with("123456", BigDecimal.valueOf(49.85), "7831", "NAO CONTEM BENEFICIO");
        Mockito.when(mccGateway.findAllMcc()).thenReturn(getMccList());
        Mockito.when(accountGateway.findAccountById(Mockito.any())).thenReturn(Optional.of(getAccount()));

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output.code());
        Assertions.assertEquals("00", output.code());

        Mockito.verify(transactionGateway, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void givenInvalidMccAndInvalidMerchantName_whenCallRegisterTransactionUseCase_ShouldReturnRejectedTransaction(){
        final var command = RegisterTransactionCommand.with("123456", BigDecimal.valueOf(1049.85), "7831", "NAO CONTEM BENEFICIO");
        Mockito.when(mccGateway.findAllMcc()).thenReturn(getMccList());
        Mockito.when(accountGateway.findAccountById(Mockito.any())).thenReturn(Optional.of(getAccount()));

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output.code());
        Assertions.assertEquals("51", output.code());

        Mockito.verify(transactionGateway, Mockito.times(1)).create(Mockito.any());
    }

    private List<Mcc> getMccList(){
        return List.of(Mcc.with(1, "5411", "FOOD"),
                Mcc.with(2, "5412", "FOOD"),
                Mcc.with(3, "5811", "MEAL"),
                Mcc.with(4, "5812", "MEAL"),
                Mcc.with(5, "999", "CASH"));
    }

    private Account getAccount(){
        return Account.newAccount("123456",
                BigDecimal.valueOf(300.10),
                BigDecimal.valueOf(400.21),
                BigDecimal.valueOf(500.34));
    }
}
