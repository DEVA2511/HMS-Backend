package com.hms.pharmacy.Service;

import com.hms.pharmacy.DTO.MedicineInventoryDTO;
import com.hms.pharmacy.Exception.HMSException;

import java.util.List;

public interface MedicineInventoryService {

    List<MedicineInventoryDTO> getAllMedicine() throws HMSException;

    MedicineInventoryDTO getMedicineById(Long id) throws HMSException;

    MedicineInventoryDTO addMedicine(MedicineInventoryDTO medicineInventory) throws HMSException;

    MedicineInventoryDTO updateMedicine(MedicineInventoryDTO medicineInventory) throws HMSException;

    void deleteMedicine(Long id) throws HMSException;

    String sellStock(Long medicineId,Integer quantity) throws HMSException;

    void deleteExpiredMedicines() throws HMSException;
}
