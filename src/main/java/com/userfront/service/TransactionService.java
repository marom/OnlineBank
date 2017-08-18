package com.userfront.service;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.SavingsTransaction;

import java.util.List;

public interface TransactionService {

    public List<PrimaryTransaction> findPrimaryAccountTransactions(String userName);

    public List<SavingsTransaction> findSavingsAccountTransactions(String userName);

    public void savePrimaryAccountDepositTransaction(PrimaryTransaction primaryTransaction);
    public void saveSavingsAccountDepositTransaction(SavingsTransaction savingsTransaction);

    public void savePrimaryAccountWithdrawTransaction(PrimaryTransaction primaryTransaction);

    public void saveSavingsAccountWithdrawTransaction(SavingsTransaction savingsTransaction);

    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;
}
