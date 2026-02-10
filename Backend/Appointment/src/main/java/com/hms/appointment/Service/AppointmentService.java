package com.hms.appointment.Service;

import com.hms.appointment.DTO.AppointmentDTO;
import com.hms.appointment.DTO.AppointmentDetails;
import com.hms.appointment.DTO.MonthlyVisitDTO;
import com.hms.appointment.DTO.ReasonCountDTO;
import com.hms.appointment.Exception.HMSException;

import java.util.List;

public interface AppointmentService {

    Long scheduleAppointment(AppointmentDTO appointmentDTO)throws HMSException;

    void cancelAppointment(Long appointmentId)throws HMSException;

    void completeAppointment(Long appointmentId) throws HMSException;

    AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) throws HMSException;

    void rescheduleAppointment(Long appointmentId, String newAppointmentDateTime) throws HMSException;
    AppointmentDTO getAppointmentDetails(Long appointmentId) throws HMSException;

    AppointmentDetails getAppointmentByDetailsWithName(Long appointmentId) throws HMSException;

    List<AppointmentDetails> getAllAppointmentsByPatientId(Long patientId) throws HMSException;
    List<AppointmentDetails> getAllAppointmentsByDoctorId(Long doctorId) throws HMSException;

    List<MonthlyVisitDTO> getAppointmentCountByMonthForPatient(Long patientId) throws HMSException;

     List<MonthlyVisitDTO> getAppointmentCountByMonthForDoctor(Long doctorId) throws HMSException;

    List<MonthlyVisitDTO> getAppointmentCount() throws HMSException;

    List<ReasonCountDTO> getReasonCountByPatientId(Long patientId) throws HMSException;
    List<ReasonCountDTO> getReasonCountByDoctor(Long doctorId) throws HMSException;
    List<ReasonCountDTO> getReasonCount() throws HMSException;
    List<AppointmentDetails> getTodaysAppointments() throws HMSException;
}
