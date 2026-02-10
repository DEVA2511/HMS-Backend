package com.hms.appointment.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hms.appointment.Modal.Appointment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private OffsetDateTime appointmentDateTime;
    private status status;
    private String reason;
    private String notes;

    public Appointment toEntity() {
        return new Appointment(
                this.id,
                this.patientId,
                this.doctorId,
                this.appointmentDateTime,
                this.status,
                this.reason,
                this.notes
        );
    }
}
