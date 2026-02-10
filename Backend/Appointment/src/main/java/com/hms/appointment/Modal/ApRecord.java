package com.hms.appointment.Modal;

import com.hms.appointment.DTO.ApRecordDTO;
import com.hms.appointment.DTO.RecordDetails;
import com.hms.appointment.Utility.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Appointment_Records")
public class ApRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private String symptoms;
    private String diagnosis;
    private String tests;
    private String notes;
    private String referral;
    private OffsetDateTime followUpDate;
    private LocalDateTime createdAt;

    public ApRecordDTO toDTO() {

        return new ApRecordDTO(
                this.id,
                this.patientId,
                this.doctorId,

                this.appointment.getId(),
                StringListConverter.convertStringToList(this.symptoms),
                this.diagnosis,
                StringListConverter.convertStringToList(this.tests),
                this.notes,
                this.referral,
                null,
                this.followUpDate,
                this.createdAt
        );
    }

    public RecordDetails toRecordDetails() {
        return new RecordDetails(
                this.id,
                this.patientId,
                this.doctorId,
                null,
                appointment.getId(),
                StringListConverter.convertStringToList(this.symptoms),
                this.diagnosis,
                StringListConverter.convertStringToList(this.tests),
                this.notes,
                this.referral,
                this.followUpDate,
                this.createdAt
        );

    }

}
