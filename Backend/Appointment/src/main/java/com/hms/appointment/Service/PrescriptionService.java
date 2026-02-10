package com.hms.appointment.Service;

import com.hms.appointment.DTO.MedicineDTO;
import com.hms.appointment.DTO.PrescriptionDTO;
import com.hms.appointment.DTO.PrescriptionDetails;
import com.hms.appointment.Exception.HMSException;

import java.util.List;

public interface PrescriptionService {
    public Long savePrescription(PrescriptionDTO prescriptionDetails) throws HMSException;

    public PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId) throws HMSException;

    public PrescriptionDTO getPrescriptionById(Long prescriptionId) throws HMSException;

//    void saveOrUpdatePrescription(PrescriptionDTO dto) throws HMSException;

    public List<PrescriptionDetails> getPrescriptionsByPatientId(Long patientId) throws HMSException;

    public List<PrescriptionDetails>getPrescriptions() throws HMSException;
    List<MedicineDTO> getMedictionByPatinetId(Long patientId) throws HMSException;
}
