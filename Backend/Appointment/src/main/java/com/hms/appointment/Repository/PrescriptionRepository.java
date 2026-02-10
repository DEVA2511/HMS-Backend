package com.hms.appointment.Repository;

import com.hms.appointment.Exception.HMSException;
import com.hms.appointment.Modal.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findByAppointment_Id(Long appointmentId) throws HMSException;

    List<Prescription> findAllByPatientId(Long patientId) throws HMSException;

    @Query("SELECT p.id FROM Prescription p WHERE p.patientId = :patientId")
    List<Long> findAllPrescriptionIdsByPatientId(Long patientId) throws HMSException;
}
