package com.hms.profile.Repository;

import com.hms.profile.DTO.DoctorDropDown;
import com.hms.profile.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);

    Optional<Doctor> findByLicenseNumber(String licenseNumber);

    @Query("SELECT d.id AS id, d.name AS name FROM Doctor d")
    List<DoctorDropDown> findAllDoctorDropDown();

    @Query("SELECT d.id AS id, d.name AS name FROM Doctor d WHERE d.id IN ?1")
    List<DoctorDropDown> findAllDoctorDropDownByIds(List<Long> id);
}
