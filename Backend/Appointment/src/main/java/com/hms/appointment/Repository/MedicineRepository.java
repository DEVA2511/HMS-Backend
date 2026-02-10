package com.hms.appointment.Repository;

import com.hms.appointment.DTO.MedicineDTO;
import com.hms.appointment.Modal.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findAllByPrescription_Id(Long prescriptionId);
    List<Medicine> findAllByPrescription_IdIn(List<Long> prescriptionIds);
   ;
}
