package com.userfront.dao;

import com.userfront.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentDao extends CrudRepository<Appointment, Long> {

}
