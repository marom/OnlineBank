package com.userfront.dao;

import com.userfront.domain.PrimaryAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PrimaryAccountDaoIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;


    @Test
    public void whenFindAllThenReturnAllPrimaryAccounts() {

        PrimaryAccount primaryAccountOne = new PrimaryAccount();
        primaryAccountOne.setAccountNumber(123456);
        primaryAccountOne.setAccountBalance(BigDecimal.valueOf(0.0));

        PrimaryAccount primaryAccountTwo = new PrimaryAccount();
        primaryAccountTwo.setAccountNumber(123457);
        primaryAccountTwo.setAccountBalance(BigDecimal.valueOf(0.0));

        entityManager.persistAndFlush(primaryAccountOne);
        entityManager.persistAndFlush(primaryAccountTwo);

        Iterable<PrimaryAccount> allPrimaryAccounts = primaryAccountDao.findAll();

        assertThat(allPrimaryAccounts).hasSize(2)
            .extracting(PrimaryAccount::getAccountNumber)
            .contains(primaryAccountOne.getAccountNumber(), primaryAccountTwo.getAccountNumber());
    }
}