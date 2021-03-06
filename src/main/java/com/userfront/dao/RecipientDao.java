package com.userfront.dao;

import com.userfront.domain.Recipient;
import org.springframework.data.repository.CrudRepository;

public interface RecipientDao extends CrudRepository<Recipient, Long> {

    Recipient findByName(String recipientName);

    void deleteByName(String recipientName);
}
