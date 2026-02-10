package com.hms.appointment.DTO;

import com.hms.appointment.Modal.Appointment;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDetails {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String doctorName;
    private Long appointmentId;
    private List<String> symptoms;
    private  String diagnosis;
    private List<String>  tests;
    private String notes;
    private String referral;
    private OffsetDateTime followUpDate;
    private LocalDateTime createdAt;
}
