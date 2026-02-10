package com.hms.pharmacy.Reposictory;

import com.hms.pharmacy.Modal.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
    List<SaleItem> findByMedicineId(Long saleId);

    List<SaleItem> findBySaleId(Long saleId);

}
