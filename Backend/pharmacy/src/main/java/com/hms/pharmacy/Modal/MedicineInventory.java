package com.hms.pharmacy.Modal;

import com.hms.pharmacy.DTO.MedicineInventoryDTO;
import com.hms.pharmacy.DTO.StockStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medicine_Id", nullable = false)
    private Medicine medicine;
    private String batchNumber;
    private int quantity;
    private LocalDate expiryDate;
    private LocalDate addedDate;
//    private Integer currentQuantity;
    private Integer initialQuantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status", length = 20, nullable = false)
    private StockStatus stockStatus;

    public MedicineInventory(Long id) {
        this.id = id;
    }

    public MedicineInventoryDTO toDTO() {
        return new MedicineInventoryDTO(
                this.id,
                this.medicine.getId(),
                this.batchNumber,
                this.quantity,
                this.expiryDate,
                this.addedDate,
                this.initialQuantity,
                this.stockStatus
        );
    }
}
