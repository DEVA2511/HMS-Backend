package com.hms.appointment.Modal;

import com.hms.appointment.DTO.MedicineDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dosage;
    private Long medicineId;
    private String frequency;
    private Integer duration;
    private String route;
    private String type;
    private String instructions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;


    public MedicineDTO toDTO() {

        return new MedicineDTO(
                this.id,
                this.name,
                this.dosage,
                this.medicineId,
                this.frequency,
                this.duration,
                this.route,
                this.type,
                this.instructions,
                prescription.getId()
        );
    }
}
