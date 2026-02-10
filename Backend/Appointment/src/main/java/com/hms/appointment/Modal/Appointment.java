package com.hms.appointment.Modal;

import com.hms.appointment.DTO.AppointmentDTO;
import com.hms.appointment.DTO.status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private Long doctorId;
    //    private LocalDateTime appointmentDate;
    @Column(name = "appointment_date_time", nullable = false)
    private OffsetDateTime appointmentDateTime;
    @Enumerated(EnumType.STRING)
    private status status;
    private String reason;
    private String notes;

    public Appointment(Long appointmentId) {
        this.id = appointmentId;
    }

    public AppointmentDTO toDto() {
        return new AppointmentDTO(
                this.id,
                this.patientId,
                this.doctorId,
                this.appointmentDateTime,
                this.status,
                this.reason,
                this.notes);
    }
}
