package com.userfront.service;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.SavingsAccount;

import java.security.Principal;

public interface AccountService {

    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();

    void deposit(String accountType, double v, Principal principal);
}
