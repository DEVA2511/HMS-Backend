package com.hms.appointment.Service.IMPL;

import com.hms.appointment.DTO.MedicineDTO;
import com.hms.appointment.Exception.HMSException;
import com.hms.appointment.Modal.Medicine;
import com.hms.appointment.Repository.MedicineRepository;
import com.hms.appointment.Service.MedicineService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicineServiceIMPL implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Override
    public Long saveMedicine(MedicineDTO request) throws HMSException {
        return medicineRepository.save(request.toEntity()).getId();
    }

    @Override
    public List<MedicineDTO> saveAllMedicine(List<MedicineDTO> medicineDTOList) throws HMSException {
      return ((List<Medicine>) medicineRepository.saveAll(
                medicineDTOList.stream().map(MedicineDTO::toEntity).toList()
        )).stream().map(
                Medicine::toDTO
        ).toList();
    }

    @Override
    public List<MedicineDTO> getAllMedicineByPrescriptionId(Long prescriptionId) throws HMSException {
        return ((List<Medicine>) medicineRepository.findAllByPrescription_Id(prescriptionId)).stream().map(
                Medicine::toDTO
        ).toList();
    }

    @Override
    public List<MedicineDTO> getMedicinesByPrescriptionId(List<Long> prescriptionIds) {
        return medicineRepository.findAllByPrescription_IdIn(prescriptionIds).stream().map(
                Medicine::toDTO
        ).toList();
    }
}
