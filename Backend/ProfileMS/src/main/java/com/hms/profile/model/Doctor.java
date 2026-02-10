package com.hms.profile.model;

import com.hms.profile.DTO.BloodGroup;
import com.hms.profile.DTO.DoctorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column( unique = true)
    private String email;
    private LocalDate dob;
    private Long profilePictureId;
//    private String gender;
    private String address;
    private String phoneNumber;
    @Column( unique = true)
    private String licenseNumber;
    private String specialization;
    private Integer totalExperience;
    private String department;

    public DoctorDTO toDTO() {
        return new DoctorDTO(
                this.id,
                this.name,
                this.email,
                this.dob,
                this.profilePictureId,
                this.address,
                this.phoneNumber,
                this.licenseNumber,
                this.specialization,
                this.totalExperience,
                this.department
        );
    }

}
