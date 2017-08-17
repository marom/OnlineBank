package com.userfront.service;

import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.SavingsTransaction;

import java.util.List;

public interface TransactionService {

    public List<PrimaryTransaction> findPrimaryAccountTransactions(String userName);
    public void savePrimaryAccountDepositTransaction(PrimaryTransaction primaryTransaction);

    public List<SavingsTransaction> findSavingsAccountTransactions(String userName);

    public void saveSavingsAccountDepositTransaction(SavingsTransaction savingsTransaction);

}
