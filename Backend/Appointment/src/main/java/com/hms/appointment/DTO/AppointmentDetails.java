package com.hms.appointment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetails {
    private Long id;
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientPhoneNumber;
    private Long doctorId;
    private String doctorName;
    private OffsetDateTime appointmentDateTime;
    private status status;
    private String reason;
    private String notes;

}
