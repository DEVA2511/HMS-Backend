package com.hms.media.clients;


import com.hms.media.config.FeignClientIntersect;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="profileMS",url = "${PROFILEMS_URL}", configuration= FeignClientIntersect.class)
public interface ProfileClient {

//    @GetMapping("/api/profile/doctor/exists/{id}")
//    Boolean doctorExists(@PathVariable("id") Long doctorId);
//    @GetMapping("/api/profile/patient/exists/{id}")
//    Boolean patientExists(@PathVariable("id") Long patientId);
//    @GetMapping("/api/profile/patient/get/{id}")
//    PatientDTO getPatientById(@PathVariable("id") Long patientId);
//    @GetMapping("/api/profile/doctor/get/{id}")
//    DoctorDTO getDoctorById(@PathVariable("id") Long doctorId);
//
//    @GetMapping("/api/profile/doctor/getDoctorsById")
//    List<DoctorName> getDoctorsById(@RequestParam("doctorIds")  List<Long> doctorIds);

}
