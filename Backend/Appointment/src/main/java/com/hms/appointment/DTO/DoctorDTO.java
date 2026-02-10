package com.hms.appointment.DTO;

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
    private String address;
    private String phoneNumber;
    private String licenseNumber;
    private String specialization;
    private Integer totalExperience;
    private String department;


}
