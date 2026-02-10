package com.hms.profile.Service.IMPL;

import com.hms.profile.DTO.DoctorDTO;
import com.hms.profile.DTO.DoctorDropDown;
import com.hms.profile.Exception.HMSException;
import com.hms.profile.Repository.DoctorRepository;
import com.hms.profile.Service.DoctorService;
import com.hms.profile.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceIMPL implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Long addDoctor(DoctorDTO doctorDTO) throws HMSException {
        if(doctorDTO.getEmail()!=null&& doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent()) throw new HMSException("DOCTOR_ALREADY_EXISTS");
    if(doctorDTO.getLicenseNumber()!=null && doctorRepository.findByLicenseNumber(doctorDTO.getLicenseNumber()).isPresent()) throw new HMSException("DOCTOR_ALREADY_EXISTS");
        return doctorRepository.save(doctorDTO.toEntity()).getId();
    }

    @Override
    public DoctorDTO getDoctorById(Long id) throws HMSException {
        return doctorRepository.findById(id).orElseThrow(()->new HMSException("PATIENT_NOT_FOUND")).toDTO();

    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) throws HMSException {
        doctorRepository.findById(doctorDTO.getId())
                .orElseThrow(() -> new HMSException("DOCTOR_NOT_FOUND"));

        return doctorRepository.save(doctorDTO.toEntity()).toDTO();
    }

    @Override
    public Boolean doctorExits(Long id) throws HMSException {
        return doctorRepository.existsById(id);
    }

    @Override
    public List<DoctorDropDown> getDoctorDropDown() throws HMSException {
        return doctorRepository.findAllDoctorDropDown();
    }

    @Override
    public List<DoctorDTO> getAllDoctors() throws HMSException {
        return ((List<Doctor>) doctorRepository.findAll()).stream().map(com.hms.profile.model.Doctor::toDTO).toList();
    }

    @Override
    public List<DoctorDropDown> getDoctorsById(List<Long> id) throws HMSException {
        return doctorRepository.findAllDoctorDropDownByIds(id);
    }
}
