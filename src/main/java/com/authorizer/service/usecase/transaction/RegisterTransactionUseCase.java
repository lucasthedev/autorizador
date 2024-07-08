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

    public RegisterTransactionUseCase(TransactionGateway transactionGateway,
                                      MccGateway mccGateway,
                                      AccountGateway accountGateway) {
        this.transactionGateway = transactionGateway;
        this.mccGateway = mccGateway;
        this.accountGateway = accountGateway;
    }

    @Override
    public RegisterTransactionOutPut execute(RegisterTransactionCommand input){
        final var amount = input.amount();
        final var mcc = input.mcc();
        final var merchant = input.merchant();

        Account accountFound = accountGateway.findAccountById(input.accountId()).get();

        RegisterTransactionOutPut outputMcc = registerTransactionByMcc(mcc, accountFound, amount, merchant);
        if (outputMcc != null) return outputMcc;

        RegisterTransactionOutPut outPutMerchant = registerTransactionByMerchantName(merchant, accountFound, amount);
        if(outPutMerchant != null) return outPutMerchant;

        RegisterTransactionOutPut outPutCasCreditLimit = registerTransactionByCashCreditLimit(accountFound, amount, merchant);
        if (outPutCasCreditLimit != null) return outPutCasCreditLimit;


        final var transaction = Transaction.newTransaction(accountFound.getId(),mcc,amount, merchant, REJECTED);
        transactionGateway.create(transaction);
        return RegisterTransactionOutPut.from(REJECTED);
    }

    private RegisterTransactionOutPut registerTransactionByMcc(String mcc, Account accountFound,
                                                               BigDecimal amount,String merchant) {
        List<Mcc> mccList = mccGateway.findAllMcc();
        for(Mcc m: mccList){
            if(m.getMccCode().equals(mcc)){
                if(m.getDescription().equals("FOOD") && accountFound.getAvailableFoodCreditLimit().compareTo(amount) >= 0){
                    accountFound.subtractFromFoodCreditLimit(amount);
                    accountGateway.update(accountFound);
                    final var transaction =
                            Transaction.newTransaction(accountFound.getId(), mcc, amount, merchant, SUCCESS);
                    transactionGateway.create(transaction);
                    return RegisterTransactionOutPut.from(SUCCESS);
                }
                else if(accountFound.getAvailableMealCreditLimit().compareTo(amount) >= 0){
                    accountFound.subtractFromMealCreditLimit(amount);
                    accountGateway.update(accountFound);
                    final var transaction =
                            Transaction.newTransaction(accountFound.getId(), mcc, amount, merchant, SUCCESS);
                    transactionGateway.create(transaction);
                    return RegisterTransactionOutPut.from(SUCCESS);
                }
            }
        }
        return null;
    }

    private RegisterTransactionOutPut registerTransactionByCashCreditLimit(Account accountFound, BigDecimal amount, String merchant) {
        if(accountFound.getAvailableCashCreditLimit().compareTo(amount) >= 0){
            accountFound.subtractFromCashCreditLimit(amount);
            accountGateway.update(accountFound);
            final var transaction =
                    Transaction.newTransaction(accountFound.getId(),"999", amount, merchant, SUCCESS);
            transactionGateway.create(transaction);
            return RegisterTransactionOutPut.from(SUCCESS);
        }
        return null;
    }

    private RegisterTransactionOutPut registerTransactionByMerchantName(String merchant, Account accountFound, BigDecimal amount) {
        String mccByMerchantName = MerchantUtils.findMccByMerchantName(merchant);
        if(!mccByMerchantName.isBlank() && mccByMerchantName.equals("5411")){
            if(accountFound.getAvailableFoodCreditLimit().compareTo(amount) >= 0 ){
                accountFound.subtractFromFoodCreditLimit(amount);
                accountGateway.update(accountFound);
                final var transaction =
                        Transaction.newTransaction(accountFound.getId(),mccByMerchantName, amount, merchant, SUCCESS);
                transactionGateway.create(transaction);
                return RegisterTransactionOutPut.from(SUCCESS);
            }
        }
        else if(!mccByMerchantName.isBlank() && accountFound.getAvailableMealCreditLimit().compareTo(amount) >= 0){
            accountFound.subtractFromMealCreditLimit(amount);
            accountGateway.update(accountFound);
            final var transaction =
                    Transaction.newTransaction(accountFound.getId(),mccByMerchantName, amount, merchant, SUCCESS);
            transactionGateway.create(transaction);
            return RegisterTransactionOutPut.from(SUCCESS);
        }
        return null;
    }

}
