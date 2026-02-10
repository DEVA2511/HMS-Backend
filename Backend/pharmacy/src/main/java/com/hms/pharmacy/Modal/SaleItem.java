package com.hms.pharmacy.Modal;

import com.hms.pharmacy.DTO.SaleItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_Id", nullable = false)
    private Sale sale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_Id", nullable = false)
    private Medicine medicine;
    private String batchNumber;
    private Integer quantity;
    private Double unitPrice;

    public SaleItem(Long id) {
        this.id = id;
    }

    public SaleItemDTO toDTO() {
        return new SaleItemDTO(
                this.id,
                this.sale !=null ?sale.getId():null,
                this.medicine !=null ?medicine.getId():null,
                this.batchNumber,
                this.quantity,
                this.unitPrice,
                this.unitPrice * this.quantity
        );
    }
}
