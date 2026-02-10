package com.hms.pharmacy.Modal;


import com.hms.pharmacy.DTO.MedicineDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dosage; //e.g., "500mg", "10ml"
    private MedicineCategory category; //antibiotic, analgesic, antiviral, etc.
    private MedicineType type; //tablet, syrup, injection, etc.
    private String manufacturer;
    private double unitPrice;
    private Integer stock;
    private LocalDateTime createdAt; //timestamp of creation

    public Medicine(Long id) {
        this.id = id;
    }

    public MedicineDTO toDTO() {
        return new MedicineDTO(
                this.id,
                this.name,
                this.dosage,
                this.category,
                this.type,
                this.manufacturer,
                this.unitPrice,
                this.stock,
                this.createdAt
        );
    }
}
