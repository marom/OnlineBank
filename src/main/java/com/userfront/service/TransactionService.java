package com.userfront.service;

import com.userfront.domain.PrimaryTransaction;

import java.util.List;

public interface TransactionService {

    public List<PrimaryTransaction> findPrimaryAccountTransactions(String userName);

    public void savePrimaryAccountDepositTransaction(PrimaryTransaction primaryTransaction);
}
