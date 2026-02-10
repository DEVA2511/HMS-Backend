package com.hms.profile.Repository;

import com.hms.profile.DTO.DoctorDropDown;
import com.hms.profile.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository  extends JpaRepository<Patient,Long> {
Optional<Patient> findByAadharNumber(String aadharNumber);
Optional<Patient> findByEmail(String email);
    @Query("SELECT d.id AS id, d.name AS name FROM Patient d WHERE d.id IN ?1")
    List<DoctorDropDown> findAllPatientDropDownByIds(List<Long> id);
}
