package com.hms.profile.Service.IMPL;

import com.hms.profile.DTO.DoctorDropDown;
import com.hms.profile.DTO.PatientDTO;
import com.hms.profile.Exception.HMSException;
import com.hms.profile.Repository.PatientRepository;
import com.hms.profile.Service.PatientService;
import com.hms.profile.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceIMPL  implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Long addPatient(PatientDTO patientDTO) throws HMSException {
        if(patientDTO.getEmail()!=null && patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) throw new HMSException("PATIENT_ALREADY_EXISTS");
        if(patientDTO.getAadharNumber()!=null&& patientRepository.findByAadharNumber(patientDTO.getAadharNumber()).isPresent()) throw new HMSException("PATIENT_ALREADY_EXISTS");
       return patientRepository.save(patientDTO.toEntity()).getId();
    }

    @Override
    public PatientDTO getPatientById(Long id) throws HMSException {
        return patientRepository.findById(id).orElseThrow(()->new HMSException("PATIENT_NOT_FOUND")).toDTO();
    }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws HMSException {
       patientRepository.findById(patientDTO.getId()).orElseThrow(()->new HMSException("PATIENT_NOT_FOUND"));
        return patientRepository.save(patientDTO.toEntity()).toDTO();
    }

    @Override
    public Boolean patientExits(Long id) throws HMSException {
        return patientRepository.existsById(id);
    }

    @Override
    public List<PatientDTO> getAllPatients() throws HMSException {
        return ((List<Patient>)  patientRepository.findAll()).stream().map(Patient::toDTO).toList();
    }

    @Override
    public List<DoctorDropDown> getPatientIds(List<Long> id) throws HMSException {
       return patientRepository.findAllPatientDropDownByIds(id);
    }


}
