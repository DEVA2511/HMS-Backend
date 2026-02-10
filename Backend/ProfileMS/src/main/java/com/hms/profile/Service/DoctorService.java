package com.hms.profile.Service;

import com.hms.profile.DTO.DoctorDTO;
import com.hms.profile.DTO.DoctorDropDown;
import com.hms.profile.Exception.HMSException;

import java.util.List;

public interface DoctorService {
     Long addDoctor(DoctorDTO doctorDTO) throws HMSException;
     DoctorDTO getDoctorById(Long id) throws HMSException;

     DoctorDTO updateDoctor( DoctorDTO doctorDTO) throws HMSException;
     public Boolean doctorExits(Long id) throws HMSException;

     public List<DoctorDropDown> getDoctorDropDown() throws HMSException;
public List<DoctorDTO> getAllDoctors() throws HMSException;
     public List<DoctorDropDown> getDoctorsById(List<Long> id) throws HMSException;
}
