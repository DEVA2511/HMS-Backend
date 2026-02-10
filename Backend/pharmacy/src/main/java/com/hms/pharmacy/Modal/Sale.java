package com.hms.pharmacy.Modal;

import com.hms.pharmacy.DTO.SaleDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long prescriptionId;
    private String buyerName;
    private String buyerContact;
    private LocalDateTime saleDate;
    private Double totalAmount;

    public Sale(Long id) {
        this.id = id;
    }
    public SaleDTO toDTO() {
        return new SaleDTO(
                this.id,
                this.prescriptionId,
                this.buyerName,
                this.buyerContact,
                this.saleDate,
                this.totalAmount
        );
    }
}
