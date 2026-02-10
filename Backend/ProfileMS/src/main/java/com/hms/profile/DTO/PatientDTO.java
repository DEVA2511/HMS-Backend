package com.hms.profile.DTO;

import com.hms.profile.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Long profilePictureId;
    private String address;
    private String phoneNumber;
    private String aadharNumber;
    private BloodGroup bloodGroup;
    private String allergies;
    private String chronicDiseases;
    public Patient toEntity() {
        return new Patient(
                this.id,
                this.name,
                this.email,
                this.dob,
                this.profilePictureId,
                this.address,
                this.phoneNumber,
                this.aadharNumber,
                this.bloodGroup,
                this.allergies,
                this.chronicDiseases
        );
    }
}
