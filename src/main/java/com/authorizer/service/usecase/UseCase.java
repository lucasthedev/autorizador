package com.authorizer.service.usecase;

import com.authorizer.service.exceptions.DomainException;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN input) throws DomainException;
}
