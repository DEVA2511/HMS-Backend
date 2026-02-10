package com.hms.appointment.Service;

import com.hms.appointment.DTO.MedicineDTO;
import com.hms.appointment.Exception.HMSException;

import java.util.List;

public interface MedicineService {
    public Long saveMedicine(MedicineDTO request) throws HMSException;

    public List<MedicineDTO> saveAllMedicine(List<MedicineDTO> medicineDTOList) throws HMSException;

    List<MedicineDTO> getAllMedicineByPrescriptionId(Long prescriptionId) throws HMSException;

    List<MedicineDTO> getMedicinesByPrescriptionId(List<Long> prescriptionIds);
}
