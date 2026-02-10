package com.hms.profile.Service;

import com.hms.profile.DTO.DoctorDropDown;
import com.hms.profile.DTO.PatientDTO;
import com.hms.profile.Exception.HMSException;

import java.util.List;

public interface PatientService {

     Long addPatient(PatientDTO patientDTO) throws HMSException;
 PatientDTO getPatientById(Long id) throws HMSException;

     PatientDTO updatePatient( PatientDTO patientDTO) throws HMSException;

     Boolean patientExits(Long id) throws HMSException;
public List<PatientDTO> getAllPatients() throws HMSException;
    public List<DoctorDropDown> getPatientIds(List<Long> id) throws HMSException;
}
