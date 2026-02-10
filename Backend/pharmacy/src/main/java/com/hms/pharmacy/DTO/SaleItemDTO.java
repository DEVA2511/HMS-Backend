package com.hms.pharmacy.DTO;

import com.hms.pharmacy.Exception.HMSException;
import com.hms.pharmacy.Modal.Medicine;
import com.hms.pharmacy.Modal.Sale;
import com.hms.pharmacy.Modal.SaleItem;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemDTO {
    private Long id;
    private Long saleId;
    private Long medicineId;
    private String batchNumber;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

public SaleItem toEntity() {
    return new SaleItem(
            this.id,
           new Sale(this.saleId),
            new Medicine(this.medicineId),
            this.batchNumber,
            this.quantity,
            this.unitPrice
    );

}
}
