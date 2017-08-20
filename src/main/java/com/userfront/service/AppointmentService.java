package com.userfront.service;

import com.userfront.domain.Appointment;
import org.springframework.stereotype.Service;


public interface AppointmentService {

    void createAppointment(Appointment appointment);
}
