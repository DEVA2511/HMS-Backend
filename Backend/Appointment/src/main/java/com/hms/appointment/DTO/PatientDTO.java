package com.hms.appointment.DTO;

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
    private String address;
    private String phoneNumber;
    private String aadharNumber;
    private BloodGroup bloodGroup;
    private String allergies;
    private String chronicDiseases;

}
