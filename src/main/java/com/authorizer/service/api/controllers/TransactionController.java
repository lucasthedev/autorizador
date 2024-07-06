package com.authorizer.service.api.controllers;

import com.authorizer.service.api.controllers.request.RegisterTransactionRequest;
import com.authorizer.service.exceptions.DomainException;
import com.authorizer.service.usecase.transaction.RegisterTransactionCommand;
import com.authorizer.service.usecase.transaction.RegisterTransactionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final RegisterTransactionUseCase registerTransactionUseCase;

    public TransactionController(RegisterTransactionUseCase registerTransactionUseCase) {
        this.registerTransactionUseCase = registerTransactionUseCase;
    }

    @PostMapping
    ResponseEntity<Object> registerTransaction(@RequestBody RegisterTransactionRequest request) throws DomainException {
        final var accountId = request.accountId();
        final var amount = request.amount();
        final var mcc = request.mcc();
        final var merchant = request.merchant();

        final var command = RegisterTransactionCommand.with(accountId,amount,mcc,merchant);

        final var outPut = registerTransactionUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.OK).body(outPut.code());
    }
}
