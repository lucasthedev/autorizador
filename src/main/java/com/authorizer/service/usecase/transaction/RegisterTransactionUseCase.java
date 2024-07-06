package com.authorizer.service.usecase.transaction;

import com.authorizer.service.domain.account.Account;
import com.authorizer.service.domain.account.AccountGateway;
import com.authorizer.service.domain.mcc.Mcc;
import com.authorizer.service.domain.mcc.MccGateway;
import com.authorizer.service.domain.transaction.Transaction;
import com.authorizer.service.domain.transaction.TransactionGateway;
import com.authorizer.service.usecase.UseCase;
import com.authorizer.service.util.MerchantUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class RegisterTransactionUseCase extends UseCase<RegisterTransactionCommand, RegisterTransactionOutPut> {
    private final TransactionGateway transactionGateway;
    private final MccGateway mccGateway;
    private final AccountGateway accountGateway;

    private final String SUCCESS = "00";
    private final String REJECTED = "51";
    private final String FODD = "FOOD";

    public RegisterTransactionUseCase(TransactionGateway transactionGateway,
                                      MccGateway mccGateway,
                                      AccountGateway accountGateway) {
        this.transactionGateway = transactionGateway;
        this.mccGateway = mccGateway;
        this.accountGateway = accountGateway;
    }

    @Override
    public RegisterTransactionOutPut execute(RegisterTransactionCommand input){
        final var accountId = input.accountId();
        final var amount = input.amount();
        final var mcc = input.mcc();
        final var merchant = input.merchant();

        List<Mcc> mccList = mccGateway.findAllMcc();
        Account accountFound = accountGateway.findAccountById(accountId).get();

        for(Mcc m: mccList){
            if(m.getMccCode().equals(mcc)){
                if(m.getDescription().equals(FODD) &&
                        accountFound.getAvailableFoodCreditLimit().compareTo(amount) >= 0){
                    accountFound.subtractFromFoodCreditLimit(amount);
                    accountGateway.update(accountFound);
                    var transaction = Transaction.newTransaction(accountId,mcc,amount, merchant, SUCCESS);
                    transactionGateway.create(transaction);
                    return RegisterTransactionOutPut.from(SUCCESS);
                }
                else if(accountFound.getAvailableMealCreditLimit().compareTo(amount) >= 0){
                    accountFound.subtractFromMealCreditLimit(amount);
                    accountGateway.update(accountFound);
                    var transaction = Transaction.newTransaction(accountId,mcc,amount, merchant, SUCCESS);
                    transactionGateway.create(transaction);
                    return RegisterTransactionOutPut.from(SUCCESS);
                }
            }
        }

        RegisterTransactionOutPut outPutMerchant = registerTransactionByMerchantName(merchant, accountFound, amount, accountId);
        if(outPutMerchant != null) return outPutMerchant;

        RegisterTransactionOutPut outPutCasCreditLimit = registerTransactionByCashCreditLimit(accountFound, amount, accountId, merchant);
        if (outPutCasCreditLimit != null) return outPutCasCreditLimit;


        final var transaction = Transaction.newTransaction(accountId,mcc,amount, merchant, REJECTED);
        transactionGateway.create(transaction);
        return RegisterTransactionOutPut.from(REJECTED);
    }

    private RegisterTransactionOutPut registerTransactionByCashCreditLimit(Account accountFound, BigDecimal amount, String accountId, String merchant) {
        if(accountFound.getAvailableCashCreditLimit().compareTo(amount) >= 0){
            accountFound.subtractFromCashCreditLimit(amount);
            accountGateway.update(accountFound);
            final var transaction = Transaction.newTransaction(accountId,"999", amount, merchant, SUCCESS);
            transactionGateway.create(transaction);
            return RegisterTransactionOutPut.from(SUCCESS);
        }
        return null;
    }

    private RegisterTransactionOutPut registerTransactionByMerchantName(String merchant, Account accountFound, BigDecimal amount, String accountId) {
        String mccByMerchantName = MerchantUtils.findMccByMerchantName(merchant);
        if(!mccByMerchantName.isBlank() && merchant.equals(FODD)){
            if(accountFound.getAvailableFoodCreditLimit().compareTo(amount) >=0 ){
                accountFound.subtractFromFoodCreditLimit(amount);
                accountGateway.update(accountFound);
                final var transaction = Transaction.newTransaction(accountId,mccByMerchantName, amount, merchant, SUCCESS);
                transactionGateway.create(transaction);
                return RegisterTransactionOutPut.from(SUCCESS);
            }
        }
        return null;
    }

}
