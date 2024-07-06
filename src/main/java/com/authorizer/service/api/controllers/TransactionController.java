package com.authorizer.service.api.controllers;

import com.authorizer.service.api.controllers.request.RegisterTransactionRequest;
import com.authorizer.service.usecase.transaction.RegisterTransactionCommand;
import com.authorizer.service.usecase.transaction.RegisterTransactionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@Tag(name = "Transactions")
public class TransactionController {

    private final RegisterTransactionUseCase registerTransactionUseCase;

    public TransactionController(RegisterTransactionUseCase registerTransactionUseCase) {
        this.registerTransactionUseCase = registerTransactionUseCase;
    }

    @PostMapping
    @Operation(summary = "Register a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created successfully. Code: 00"),
            @ApiResponse(responseCode = "200", description = "Rejected. Code: 51"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown"),
    })
    ResponseEntity<Object> registerTransaction(@RequestBody RegisterTransactionRequest request) {
        final var accountId = request.accountId();
        final var amount = request.amount();
        final var mcc = request.mcc();
        final var merchant = request.merchant();

        final var command = RegisterTransactionCommand.with(accountId,amount,mcc,merchant);

        final var outPut = registerTransactionUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.OK).body("Code: " + outPut.code());
    }
}
