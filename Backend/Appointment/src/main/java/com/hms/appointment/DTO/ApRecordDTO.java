package com.hms.appointment.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hms.appointment.Modal.ApRecord;
import com.hms.appointment.Modal.Appointment;
import com.hms.appointment.Utility.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApRecordDTO {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private Long appointmentId;
    private List<String> symptoms;
    private String diagnosis;
    private List<String> tests;
    private String notes;
    private String referral;
    private PrescriptionDTO prescription;
    private OffsetDateTime followUpDate;
    private LocalDateTime createdAt;

    public ApRecord toEntity() {
        return new ApRecord(
                id,
                doctorId,
                patientId,
                new Appointment(appointmentId),
                StringListConverter.convertListToString(this.symptoms),
                diagnosis,
                StringListConverter.convertListToString(this.tests),
                notes,
                referral,
                followUpDate,
                createdAt
        );
    }
}
