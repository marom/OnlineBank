package com.userfront.service.serviceImpl;


import com.userfront.dao.PrimaryAccountDao;
import com.userfront.dao.SavingsAccountDao;
import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.User;
import com.userfront.service.AccountService;
import com.userfront.service.TransactionService;
import com.userfront.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class AccountServiceImplTest {

    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private PrimaryAccountDao primaryAccountDao;

    @MockBean
    private SavingsAccountDao savingsAccountDao;

    @MockBean
    private UserService userService;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private Principal principal;

    @MockBean
    private User user;

    @Test
    public void whenCreatePrimaryAccountThenShouldReturnThisAccount() {

        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountNumber(23456790);
        primaryAccount.setAccountBalance(BigDecimal.valueOf(0.0));

        ReflectionTestUtils.setField(accountService, "NEXT_ACCOUNT_NUMBER", 23456789);
        Mockito.when(primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber())).thenReturn(primaryAccount);

        PrimaryAccount createdPrimaryAccount = accountService.createPrimaryAccount();
        PrimaryAccount foundPrimaryAccount = primaryAccountDao.findByAccountNumber(createdPrimaryAccount.getAccountNumber());

        assertThat(createdPrimaryAccount.getAccountNumber()).isEqualTo(foundPrimaryAccount.getAccountNumber());
    }

    @Test
    public void whenCreateSavingsAccountThenShouldReturnThisAccount() {

        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountNumber(23456790);
        savingsAccount.setAccountBalance(BigDecimal.valueOf(0.0));

        ReflectionTestUtils.setField(accountService, "NEXT_ACCOUNT_NUMBER", 23456789);
        Mockito.when(savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber())).thenReturn(savingsAccount);

        SavingsAccount createdSavingsAccount = accountService.createSavingsAccount();
        SavingsAccount foundSavingsAccount = savingsAccountDao.findByAccountNumber(createdSavingsAccount.getAccountNumber());

        assertThat(createdSavingsAccount.getAccountNumber()).isEqualTo(foundSavingsAccount.getAccountNumber());
    }

    @Test
    public void whenDepositToPrimaryThenShouldIncreaseAccountBalanceByThisAmount() {

        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountNumber(23456790);
        primaryAccount.setAccountBalance(BigDecimal.valueOf(0.0));


        Mockito.when(principal.getName()).thenReturn("maro");
        Mockito.when(userService.findByUsername(principal.getName())).thenReturn(user);
        Mockito.when(user.getPrimaryAccount()).thenReturn(primaryAccount);

        accountService.deposit("Primary", 100.00, principal);

        assertThat(primaryAccount.getAccountBalance()).isEqualTo(BigDecimal.valueOf(100.00));
    }

    @Test
    public void whenDepositToSavingsThenShouldIncreaseAccountBalanceByThisAmount() {

        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountNumber(23456790);
        savingsAccount.setAccountBalance(BigDecimal.valueOf(500.0));


        Mockito.when(principal.getName()).thenReturn("maro");
        Mockito.when(userService.findByUsername(principal.getName())).thenReturn(user);
        Mockito.when(user.getSavingsAccount()).thenReturn(savingsAccount);

        accountService.deposit("Savings", 200.00, principal);

        assertThat(savingsAccount.getAccountBalance()).isEqualTo(BigDecimal.valueOf(700.00));
    }

    @Test
    public void whenWithdrawFromPrimaryThenShouldDecreaseAccountBalanceByThisAmount() {

        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountNumber(23456790);
        primaryAccount.setAccountBalance(BigDecimal.valueOf(1000.0));


        Mockito.when(principal.getName()).thenReturn("maro");
        Mockito.when(userService.findByUsername(principal.getName())).thenReturn(user);
        Mockito.when(user.getPrimaryAccount()).thenReturn(primaryAccount);

        accountService.withdraw("Primary", 100.00, principal);

        assertThat(primaryAccount.getAccountBalance()).isEqualTo(BigDecimal.valueOf(900.00));
    }

    @Test
    public void whenWithdrawFromSavingsThenShouldDecreaseAccountBalanceByThisAmount() {

        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountNumber(23456790);
        savingsAccount.setAccountBalance(BigDecimal.valueOf(500.0));


        Mockito.when(principal.getName()).thenReturn("maro");
        Mockito.when(userService.findByUsername(principal.getName())).thenReturn(user);
        Mockito.when(user.getSavingsAccount()).thenReturn(savingsAccount);

        accountService.withdraw("Savings", 500.00, principal);

        assertThat(savingsAccount.getAccountBalance()).isEqualTo(BigDecimal.valueOf(0.00));
    }
}