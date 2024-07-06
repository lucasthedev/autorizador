package com.authorizer.service.api.controllers;

import com.authorizer.service.api.controllers.request.CreateAccountRequest;
import com.authorizer.service.usecase.account.CreateAccountCommand;
import com.authorizer.service.usecase.account.CreateAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @PostMapping
    ResponseEntity<Object> createAccount(@RequestBody CreateAccountRequest request) {
        final var command = CreateAccountCommand.with(request.documentNumber(), request.availableFoodCreditLimit(),
                request.availableMealCreditLimit(),request.availableCashCreditLimit());

        final var accountOutPut = createAccountUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(accountOutPut);
    }
}
