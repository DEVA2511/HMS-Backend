package com.hms.user.clients;


import com.hms.user.DTO.DoctorDTO;
import com.hms.user.DTO.PatientDTO;
import com.hms.user.DTO.UserDTO;
import com.hms.user.config.FeignClientIntersect;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="ProfileMS",url = "${profilems.url}",configuration= FeignClientIntersect.class)
public interface ProfileClient {

    @PostMapping("/api/profile/doctor/createDoctor")
    Long addDoctor(@RequestBody DoctorDTO doctorDTO);

    @PostMapping("/api/profile/patient/createPatient")
    Long addPatient(@RequestBody PatientDTO patientDTO);

}
