package com.hms.pharmacy.DTO;

import com.hms.pharmacy.Modal.Medicine;
import com.hms.pharmacy.Modal.MedicineInventory;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineInventoryDTO {
    private Long id;
    private Long medicineId;
    private String batchNumber;
    private Integer quantity;
    private LocalDate expiryDate;
    private LocalDate addedDate;
    private Integer initialQuantity;
    private StockStatus stockStatus;

    public MedicineInventory toEntity() {
        return new MedicineInventory(
                this.id,
                new Medicine(this.medicineId),
                this.batchNumber,
                this.quantity,
                this.expiryDate,
                this.addedDate,
                this.initialQuantity,
                this.stockStatus
        );
    }
}
