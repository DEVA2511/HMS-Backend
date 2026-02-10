package com.hms.pharmacy.DTO;

import com.hms.pharmacy.Modal.Medicine;
import com.hms.pharmacy.Modal.MedicineCategory;
import com.hms.pharmacy.Modal.MedicineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {
    private Long id;
    private String name;
    private String dosage;
    private MedicineCategory category;
    private MedicineType type;
    private String manufacturer;
    private double unitPrice;
    private Integer stock;
    private LocalDateTime createdAt;

    public Medicine toEntity(){
        return new Medicine(
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
