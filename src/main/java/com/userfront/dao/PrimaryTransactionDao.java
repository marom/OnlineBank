package com.userfront.dao;

import com.userfront.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

}
