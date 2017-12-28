package com.userfront.dao;

import com.userfront.domain.PrimaryAccount;
import org.junit.Before;
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

    private PrimaryAccount primaryAccountOne, primaryAccountTwo;

    @Before
    public void setUp() {

        primaryAccountOne = new PrimaryAccount();
        primaryAccountOne.setAccountNumber(123456);
        primaryAccountOne.setAccountBalance(BigDecimal.valueOf(0.0));

        primaryAccountTwo = new PrimaryAccount();
        primaryAccountTwo.setAccountNumber(123457);
        primaryAccountTwo.setAccountBalance(BigDecimal.valueOf(0.0));

        entityManager.persist(primaryAccountOne);
        entityManager.persist(primaryAccountTwo);
        entityManager.flush();
    }

    @Test
    public void whenFindAllThenReturnAllPrimaryAccounts() {

        Iterable<PrimaryAccount> allPrimaryAccounts = primaryAccountDao.findAll();

        assertThat(allPrimaryAccounts).hasSize(2)
            .extracting(PrimaryAccount::getAccountNumber)
            .contains(primaryAccountOne.getAccountNumber(), primaryAccountTwo.getAccountNumber());
    }

    @Test
    public void whenFindByAccountNumberWithValidNumberThenReturnPrimaryAccount() {

        PrimaryAccount primaryAccountFound = primaryAccountDao.findByAccountNumber(123456);

        assertThat(primaryAccountFound.getAccountNumber()).isEqualTo(primaryAccountOne.getAccountNumber());
    }

    @Test
    public void whenFindByAccountNumberWithInvalidNumberThenReturnNull() {

        PrimaryAccount primaryAccountFound = primaryAccountDao.findByAccountNumber(12345);

        assertThat(primaryAccountFound).isNull();
    }

    @Test
    public void whenFindByIdWithValidIdThenReturnPrimaryAccount() {

        PrimaryAccount primaryAccountFound = primaryAccountDao.findOne(primaryAccountTwo.getId());

        assertThat(primaryAccountFound.getAccountNumber()).isEqualTo(primaryAccountTwo.getAccountNumber());
    }

    @Test
    public void whenFindByIdWithInvalidIdThenReturnNull() {

        PrimaryAccount primaryAccountFound = primaryAccountDao.findOne(-1L);

        assertThat(primaryAccountFound).isNull();
    }
}