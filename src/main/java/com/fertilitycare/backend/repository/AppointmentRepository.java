package com.fertilitycare.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fertilitycare.backend.DTO.AppointmentStatsDTO;
import com.fertilitycare.backend.entity.Appointment;
import com.fertilitycare.backend.entity.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomer(User user);

    List<Appointment> findByServiceDoctor(User doctor);

    List<Appointment> findByDoctor(User doctor);

    @Query("SELECT a.status, COUNT(a) FROM Appointment a GROUP BY a.status")
    List<Object[]> countAppointmentsByStatus();

    @Query("SELECT DATE(a.appointmentTime), COUNT(a) FROM Appointment a GROUP BY DATE(a.appointmentTime)")
    List<Object[]> countAppointmentsByDate();

    @Query("SELECT new com.fertilitycare.backend.dto.AppointmentStatsDTO(a.service.name, COUNT(a)) FROM Appointment a GROUP BY a.service.name")
    List<AppointmentStatsDTO> countAppointmentsByService();

}
