package com.hms.appointment.Service.IMPL;

import com.hms.appointment.DTO.*;
import com.hms.appointment.Exception.HMSException;
import com.hms.appointment.Modal.Appointment;
import com.hms.appointment.Repository.AppointmentRepository;
import com.hms.appointment.Service.ApiService;
import com.hms.appointment.Service.AppointmentService;
import com.hms.appointment.clients.ProfileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;


@Service
public class AppointmentServiceIMPL implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ProfileClient profileClient;

    @Override
    public Long scheduleAppointment(AppointmentDTO appointmentDTO) throws HMSException {
        Boolean doctorExists = profileClient.doctorExists(appointmentDTO.getDoctorId());
        if (doctorExists == null || !doctorExists) {
            throw new HMSException("DOCTOR_NOT_FOUND");
        }
        Boolean patientExists = profileClient.patientExists(appointmentDTO.getPatientId());
        if (patientExists == null || !patientExists) {
            throw new HMSException("PATIENT_NOT_FOUND");
        }
        appointmentDTO.setStatus(status.SCHEDULED);
        return appointmentRepository.save(appointmentDTO.toEntity()).getId();

    }

    @Override
    public void cancelAppointment(Long appointmentId) throws HMSException {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new HMSException("APPOINTMENT_NOT_FOUND"));
        if (appointment.getStatus() == status.CANCELLED) {
            throw new HMSException("APPOINTMENT_ALREADY_CANCELLED");
        }
        appointment.setStatus(status.CANCELLED);
        appointmentRepository.save(appointment);
    }

    @Override
    public void completeAppointment(Long appointmentId) throws HMSException {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new HMSException("APPOINTMENT_NOT_FOUND"));
        if (appointment.getStatus() == status.COMPLETED) {
            throw new HMSException("APPOINTMENT_ALREADY_COMPLETED");
        }
        appointment.setStatus(status.COMPLETED);
        appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) throws HMSException {
        Appointment existingAppointment = appointmentRepository.findById(appointmentDTO.getId())
                .orElseThrow(() -> new HMSException("APPOINTMENT_NOT_FOUND"));

        // Validate and update doctorId if provided and changed
        if (appointmentDTO.getDoctorId() != null && !appointmentDTO.getDoctorId().equals(existingAppointment.getDoctorId())) {
            Boolean doctorExists = profileClient.doctorExists(appointmentDTO.getDoctorId());
            if (doctorExists == null || !doctorExists) {
                throw new HMSException("DOCTOR_NOT_FOUND");
            }
            existingAppointment.setDoctorId(appointmentDTO.getDoctorId());
        }

        // Validate and update patientId if provided and changed
        if (appointmentDTO.getPatientId() != null && !appointmentDTO.getPatientId().equals(existingAppointment.getPatientId())) {
            Boolean patientExists = profileClient.patientExists(appointmentDTO.getPatientId());
            if (patientExists == null || !patientExists) {
                throw new HMSException("PATIENT_NOT_FOUND");
            }
            existingAppointment.setPatientId(appointmentDTO.getPatientId());
        }

        // Update other mutable fields (do not change status)
        if (appointmentDTO.getAppointmentDateTime() != null) {
            existingAppointment.setAppointmentDateTime(appointmentDTO.getAppointmentDateTime());
        }
        if (appointmentDTO.getReason() != null) {
            existingAppointment.setReason(appointmentDTO.getReason());
        }
        // Add additional field updates here if your Appointment entity has them,
        // e.g. notes, location, etc., checking for null before setting.

        Appointment saved = appointmentRepository.save(existingAppointment);
        return saved.toDto();
    }

    @Override
    public void rescheduleAppointment(Long appointmentId, String newAppointmentDateTime) throws HMSException {
//        Appointment appointment= appointmentRepository.findById(appointmentId).orElseThrow(() -> new HMSException("APPOINTMENT_NOT_FOUND"))
//        if(appointment.getStatus()== status.CANCELLED) {
//            throw new HMSException("APPOINTMENT_RESCHEDULED");
//        }
//        appointment.setAppointmentTime(LocalDateTime.parse(newAppointmentDateTime));
//        appointment.setStatus(status.SCHEDULED);
//        appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentDTO getAppointmentDetails(Long appointmentId) throws HMSException {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new HMSException("APPOINTMENT_NOT_FOUND")).toDto();

    }

    @Override
    public AppointmentDetails getAppointmentByDetailsWithName(Long appointmentId) throws HMSException {
        AppointmentDTO appointmentDTO = appointmentRepository.findById(appointmentId).orElseThrow(() -> new HMSException("APPOINTMENT_NOT_FOUND")).toDto();
        DoctorDTO doctorDTO = profileClient.getDoctorById(appointmentDTO.getDoctorId());
        PatientDTO patientDTO = profileClient.getPatientById(appointmentDTO.getPatientId());
        return new AppointmentDetails(appointmentDTO.getId(), appointmentDTO.getPatientId(), patientDTO.getName(), patientDTO.getEmail(), patientDTO.getPhoneNumber(), appointmentDTO.getDoctorId(), doctorDTO.getName(), appointmentDTO.getAppointmentDateTime(), appointmentDTO.getStatus(), appointmentDTO.getReason(), appointmentDTO.getReason());
    }

    @Override
    public List<AppointmentDetails> getAllAppointmentsByPatientId(Long patientId) throws HMSException {
        return appointmentRepository.findByPatientId(patientId).stream()
                .map(appointment -> {
                    DoctorDTO doctorDTO = profileClient.getDoctorById(appointment.getDoctorId());
                    appointment.setDoctorName(doctorDTO.getName());
                    return appointment;
                }).toList();

    }

    @Override
    public List<AppointmentDetails> getAllAppointmentsByDoctorId(Long doctorId) throws HMSException {
        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(appointment -> {
                    PatientDTO patientDTO = profileClient.getPatientById(appointment.getPatientId());
                    appointment.setPatientName(patientDTO.getName());
                    appointment.setPatientPhoneNumber(patientDTO.getPhoneNumber());
                    appointment.setPatientEmail(patientDTO.getEmail());
                    return appointment;
                }).toList();
    }

    @Override
    public List<MonthlyVisitDTO> getAppointmentCountByMonthForPatient(Long patientId) throws HMSException {
        return appointmentRepository.getMonthlyVisitsByPatient(patientId);
    }

    @Override
    public List<MonthlyVisitDTO> getAppointmentCountByMonthForDoctor(Long doctorId) throws HMSException {
        return appointmentRepository.getMonthlyVisitsByDoctor(doctorId);
    }

    @Override
    public List<MonthlyVisitDTO> getAppointmentCount() throws HMSException {
        return appointmentRepository.countCurrentYearVisits();
    }

    @Override
    public List<ReasonCountDTO> getReasonCountByPatientId(Long patientId) throws HMSException {
        return appointmentRepository.countReasonByPatientId(patientId);
    }

    @Override
    public List<ReasonCountDTO> getReasonCountByDoctor(Long doctorId) throws HMSException {
        return appointmentRepository.countReasonByDoctorId(doctorId);
    }

    @Override
    public List<ReasonCountDTO> getReasonCount() throws HMSException {
        return appointmentRepository.countReason();
    }

    @Override
    public List<AppointmentDetails> getTodaysAppointments() throws HMSException {

        LocalDate today = LocalDate.now();
        OffsetDateTime startOfDay = today.atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
        OffsetDateTime endOfDay = today.atTime(LocalTime.MAX).atOffset(OffsetDateTime.now().getOffset());
        return appointmentRepository.findByAppointmentDateTimeBetween(startOfDay, endOfDay).stream().map(appointment -> {
            DoctorDTO doctorDTO = profileClient.getDoctorById(appointment.getDoctorId());
            PatientDTO patientDTO = profileClient.getPatientById(appointment.getPatientId());
            return new AppointmentDetails(appointment.getId(),
                    appointment.getPatientId(), patientDTO.getName(),
                    patientDTO.getEmail(), patientDTO.getPhoneNumber(),
                    appointment.getDoctorId(), doctorDTO.getName(), appointment.getAppointmentDateTime(),
                    appointment.getStatus(), appointment.getReason(), appointment.getNotes());
        }).toList();

    }
}

