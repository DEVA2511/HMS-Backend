package com.hms.appointment.Repository;

import com.hms.appointment.DTO.AppointmentDetails;
import com.hms.appointment.DTO.MonthlyVisitDTO;
import com.hms.appointment.DTO.ReasonCountDTO;
import com.hms.appointment.Modal.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query("select new com.hms.appointment.DTO.AppointmentDetails(" +
            "a.id, a.patientId, null, null, null, " +
            "a.doctorId, null, a.appointmentDateTime, a.status, a.reason, a.notes) " +
            "from Appointment a " +
            "where a.patientId = ?1 ")
    List<AppointmentDetails> findByPatientId(Long patientId);


    @Query("select new com.hms.appointment.DTO.AppointmentDetails(" +
            "a.id, a.patientId, null, null, null, " +
            "a.doctorId, null, a.appointmentDateTime, a.status, a.reason, a.notes) " +
            "from Appointment a " +
            "where a.doctorId = ?1 ")
    List<AppointmentDetails> findByDoctorId(Long doctorId);

    @Query("SELECT new com.hms.appointment.DTO.MonthlyVisitDTO(CAST(FUNCTION('MONTHNAME', a.appointmentDateTime) AS string), COUNT(a.id)) " +
            "FROM Appointment a " +
            "WHERE a.patientId = :patientId AND YEAR(a.appointmentDateTime) = YEAR(CURRENT_DATE) " +
            "GROUP BY FUNCTION('MONTH', a.appointmentDateTime), CAST(FUNCTION('MONTHNAME', a.appointmentDateTime) AS string) " +
            "ORDER BY FUNCTION('MONTH', a.appointmentDateTime)")
    List<MonthlyVisitDTO> getMonthlyVisitsByPatient(@Param("patientId") Long patientId);

    @Query("SELECT new com.hms.appointment.DTO.MonthlyVisitDTO(CAST(FUNCTION('MONTHNAME', a.appointmentDateTime) AS string), COUNT(a.id)) " +
            "FROM Appointment a " +
            "WHERE a.doctorId = :doctorId AND YEAR(a.appointmentDateTime) = YEAR(CURRENT_DATE) " +
            "GROUP BY FUNCTION('MONTH', a.appointmentDateTime), CAST(FUNCTION('MONTHNAME', a.appointmentDateTime) AS string) " +
            "ORDER BY FUNCTION('MONTH', a.appointmentDateTime)")
    List<MonthlyVisitDTO> getMonthlyVisitsByDoctor(@Param("doctorId") Long doctorId);

    @Query("SELECT new com.hms.appointment.DTO.MonthlyVisitDTO(CAST(FUNCTION('MONTHNAME', a.appointmentDateTime) AS string), COUNT(a.id)) " +
            "FROM Appointment a " +
            "WHERE YEAR(a.appointmentDateTime) = YEAR(CURRENT_DATE) " +
            "GROUP BY FUNCTION('MONTH', a.appointmentDateTime), CAST(FUNCTION('MONTHNAME', a.appointmentDateTime) AS string) " +
            "ORDER BY FUNCTION('MONTH', a.appointmentDateTime)")
    List<MonthlyVisitDTO> countCurrentYearVisits();

    @Query("SELECT new com.hms.appointment.DTO.ReasonCountDTO(a.reason, COUNT(a.id)) " +
            "FROM Appointment a " +
            "WHERE a.patientId = :patientId " +
            "GROUP BY a.reason")
    List<ReasonCountDTO> countReasonByPatientId(Long patientId);

    @Query("SELECT new com.hms.appointment.DTO.ReasonCountDTO(a.reason, COUNT(a.id)) " +
            "FROM Appointment a " +
            "WHERE a.doctorId = :doctorId " +
            "GROUP BY a.reason")
    List<ReasonCountDTO> countReasonByDoctorId(Long doctorId);

    @Query("SELECT new com.hms.appointment.DTO.ReasonCountDTO(a.reason, COUNT(a.id)) " +
            "FROM Appointment a " +
            "GROUP BY a.reason")
    List<ReasonCountDTO> countReason();

    List<Appointment> findByAppointmentDateTimeBetween(java.time.OffsetDateTime startDateTime, java.time.OffsetDateTime endDateTime);


}
