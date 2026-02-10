package com.hms.pharmacy.DTO;

import com.hms.pharmacy.Modal.Sale;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Long id;
    private Long prescriptionId;
    private String buyerName;
    private String buyerContact;

    private LocalDateTime saleDate;
    private Double totalAmount;

    public Sale toEntity() {
        return new Sale(
                this.id,
                this.prescriptionId,
                this.buyerName,
                this.buyerContact,
                this.saleDate,
                this.totalAmount
        );
    }
}