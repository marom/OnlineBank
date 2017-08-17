package com.userfront.service.serviceImpl;

import com.userfront.dao.PrimaryTransactionDao;
import com.userfront.dao.SavingsTransactionsDao;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.SavingsTransaction;
import com.userfront.domain.User;
import com.userfront.service.TransactionService;
import com.userfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private UserService userService;

    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    private SavingsTransactionsDao savingsTransactionsDao;

    @Override
    public List<PrimaryTransaction> findPrimaryAccountTransactions(String username) {

        User user = userService.findByUsername(username);
        return user.getPrimaryAccount().getPrimaryTransactionList();
    }

    public void savePrimaryAccountDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDao.save(primaryTransaction);
    }

    @Override
    public List<SavingsTransaction> findSavingsAccountTransactions(String userName) {

        User user = userService.findByUsername(userName);
        return user.getSavingsAccount().getSavingsTransactionList();
    }

    @Override
    public void saveSavingsAccountDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionsDao.save(savingsTransaction);
    }


}
