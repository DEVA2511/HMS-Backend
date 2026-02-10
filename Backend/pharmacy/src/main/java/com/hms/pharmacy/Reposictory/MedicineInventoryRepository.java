package com.hms.pharmacy.Reposictory;

import com.hms.pharmacy.DTO.StockStatus;
import com.hms.pharmacy.Modal.MedicineInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicineInventoryRepository extends JpaRepository<MedicineInventory,Long> {
    List<MedicineInventory> findByExpiryDateBefore(LocalDate now);

//    medicine and expiryDate>now and quantity>0 top 1 axs by expirydate
    List<MedicineInventory> findByMedicineIdAndExpiryDateAfterAndQuantityGreaterThanAndStockStatusEqualsOrderByExpiryDateAsc(Long medicineId, LocalDate date, Integer quantity, StockStatus status);
}
