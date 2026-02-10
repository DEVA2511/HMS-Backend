package com.hms.pharmacy.Service;

import com.hms.pharmacy.DTO.MedicineDTO;
import com.hms.pharmacy.Exception.HMSException;

import java.util.List;

public interface MedicineService {
    public Long addMedicine(MedicineDTO medicineDTO) throws HMSException;

    public MedicineDTO getMedicineById(Long id) throws HMSException;

    public void updateMedicine(MedicineDTO medicineDTO) throws HMSException;

    public void deleteMedicine(Long id) throws HMSException;

    public List<MedicineDTO> getAllMedicines() throws HMSException;

    public Integer getStockById(Long id) throws HMSException;

    public void addStock(Long id, Integer quantity) throws HMSException;

    public Integer removeStock(Long id, Integer quantity) throws HMSException;
}
