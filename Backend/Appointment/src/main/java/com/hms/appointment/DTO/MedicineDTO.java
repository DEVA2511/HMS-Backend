package com.hms.appointment.DTO;

import com.hms.appointment.Modal.Medicine;
import com.hms.appointment.Modal.Prescription;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {
    private Long id;
    private String name;
    private String dosage;
    private Long medicineId;
    private String frequency;
    private Integer duration;
    private String route;
    private String type;
    private String instructions;
    private Long prescriptionId;

    public Medicine toEntity() {
        return new Medicine(
                this.id,
                this.name,
                this.dosage,
                this.medicineId,
                this.frequency,
                this.duration,
                this.route,
                this.type,
                this.instructions,
                new Prescription(prescriptionId)
        );
    }
}
