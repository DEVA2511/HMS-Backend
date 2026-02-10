
package com.hms.profile.Controller;


import com.hms.profile.DTO.DoctorDropDown;
import com.hms.profile.DTO.PatientDTO;
import com.hms.profile.Exception.HMSException;
import com.hms.profile.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/profile/patient")
@Validated
public class PatientController {

    @Autowired
    private PatientService patientService;

//    create patient api
    @PostMapping("/createPatient")
    public ResponseEntity<Long> addPatient(@RequestBody PatientDTO patientDTO) throws HMSException {
        return new ResponseEntity<>(patientService.addPatient(patientDTO), HttpStatus.CREATED);
         }

//         get by id
    @GetMapping("/get/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) throws HMSException {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

//    update patient api
    @PutMapping("/update")
    public ResponseEntity<PatientDTO> updatePatient( @RequestBody PatientDTO patientDTO) throws HMSException {

        return new ResponseEntity<>( patientService.updatePatient(patientDTO), HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> patientExists(@PathVariable Long id) throws HMSException {
        return new ResponseEntity<>(patientService.patientExits(id), HttpStatus.OK);
    }
    @GetMapping("/getPatientById")
    public ResponseEntity<List<DoctorDropDown>> getPatientsById(@RequestParam List<Long> doctorIds) throws HMSException {
        return new ResponseEntity<>(patientService.getPatientIds(doctorIds), HttpStatus.OK);
    }
    @GetMapping("getAll")
    public ResponseEntity<List<PatientDTO>> getAllPatients() throws HMSException {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }
}
