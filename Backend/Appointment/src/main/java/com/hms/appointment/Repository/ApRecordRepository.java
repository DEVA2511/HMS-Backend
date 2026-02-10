package com.hms.appointment.Repository;

import com.hms.appointment.Modal.ApRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApRecordRepository extends JpaRepository<ApRecord, Long> {

    Optional<ApRecord> findByAppointmentId(Long appointmentId);

    List<ApRecord> findByPatientId(Long patientId);

    Boolean existsByAppointmentId(Long appointmentId);
}
