package com.hms.profile.model;

import com.hms.profile.DTO.BloodGroup;
import com.hms.profile.DTO.PatientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column( unique = true)
    private String email;
    private LocalDate dob;
    private Long profilePictureId;
    private String address;
    private String phoneNumber;
    @Column( unique = true)
    private String aadharNumber;

    private BloodGroup bloodGroup;
    private String allergies;
    private String chronicDiseases;

    public PatientDTO toDTO() {
        return new PatientDTO(
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
