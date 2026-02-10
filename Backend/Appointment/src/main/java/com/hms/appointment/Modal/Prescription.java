package com.hms.appointment.Modal;

import com.hms.appointment.DTO.PrescriptionDTO;
import com.hms.appointment.DTO.PrescriptionDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private LocalDate prescriptionDate;
    private String notes;


    public Prescription(Long id) {
        this.id = id;
    }

    public PrescriptionDTO toDTO() {
        return new PrescriptionDTO(
                this.id,
                this.patientId,
                this.doctorId,

                this.appointment.getId(),
                this.prescriptionDate,
                this.notes,
                null
        );
    }

    public PrescriptionDetails tpDetails() {
        return new PrescriptionDetails(
                this.id,
                this.patientId,
                this.doctorId,
                null,
                null,
                this.appointment.getId(),
                this.prescriptionDate,
                null,
                this.notes,
                null
        );
    }
}
