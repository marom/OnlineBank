package com.userfront.dao;

import com.userfront.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

public interface SavingsTransactionsDao extends CrudRepository<SavingsTransaction, Long> {
}
