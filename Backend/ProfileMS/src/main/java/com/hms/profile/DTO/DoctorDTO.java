package com.hms.profile.DTO;

import com.hms.profile.model.Doctor;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorDTO {
private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Long profilePictureId;
    private String address;
    private String phoneNumber;
    private String licenseNumber;
    private String specialization;
    private Integer totalExperience;
    private String department;

    public Doctor toEntity(){
        return new Doctor(
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
