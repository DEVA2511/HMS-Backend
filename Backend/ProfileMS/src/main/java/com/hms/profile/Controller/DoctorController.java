package com.hms.profile.Controller;

import com.hms.profile.DTO.DoctorDTO;
import com.hms.profile.DTO.DoctorDropDown;
import com.hms.profile.Exception.HMSException;
import com.hms.profile.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/profile/doctor")
@Validated
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/createDoctor")
    public ResponseEntity<Long> addDoctor(@RequestBody DoctorDTO doctorDTO) throws HMSException {
        return new ResponseEntity<>(doctorService.addDoctor(doctorDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDTO> getDoctorsById(@PathVariable Long id) throws HMSException {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    //    update doctor api
    @PutMapping("/update")
    public DoctorDTO updateDoctor(@RequestBody DoctorDTO doctorDTO)
            throws HMSException {
        return doctorService.updateDoctor(doctorDTO);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> doctorExists(@PathVariable Long id) throws HMSException {
        return new ResponseEntity<>(doctorService.doctorExits(id), HttpStatus.OK);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DoctorDropDown>> getDoctorDropDown() throws HMSException {
        return new ResponseEntity<>(doctorService.getDoctorDropDown(), HttpStatus.OK);
    }

    @GetMapping("/getDoctorsById")
    public ResponseEntity<List<DoctorDropDown>> getDoctorsById(@RequestParam List<Long> doctorIds) throws HMSException {
    return new ResponseEntity<>(doctorService.getDoctorsById(doctorIds), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() throws HMSException {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
}
