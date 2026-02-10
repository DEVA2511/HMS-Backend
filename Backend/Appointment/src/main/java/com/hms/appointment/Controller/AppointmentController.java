package com.hms.appointment.Controller;

import com.hms.appointment.DTO.*;
import com.hms.appointment.Exception.HMSException;
import com.hms.appointment.Service.AppointmentService;
import com.hms.appointment.Service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin
@Validated
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/schedule")
    public ResponseEntity<Long> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO) throws HMSException {
        return new ResponseEntity<>(appointmentService.scheduleAppointment(appointmentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable("appointmentId") Long appointmentId) throws HMSException {
        appointmentService.cancelAppointment(appointmentId);
        return new ResponseEntity<>("Appointment Canceled", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AppointmentDTO> updateAppointment(
            @RequestBody @Valid AppointmentDTO appointmentDTO) throws HMSException {
        // Ensure incoming status is ignored at controller level as well
        appointmentDTO.setStatus(null);
        AppointmentDTO updated = appointmentService.updateAppointment(appointmentDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/get/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentDetails(@PathVariable("appointmentId") Long appointmentId) throws HMSException {

        return new ResponseEntity<>(appointmentService.getAppointmentDetails(appointmentId), HttpStatus.OK);

    }

    @GetMapping("/get/details/{appointmentId}")
    public ResponseEntity<AppointmentDetails> getAppointmentDetailsWithName(@PathVariable Long appointmentId) throws HMSException {

        return new ResponseEntity<>(appointmentService.getAppointmentByDetailsWithName(appointmentId), HttpStatus.OK);

    }

    @GetMapping("/getAllPatient/{patientId}")
    public ResponseEntity<List<AppointmentDetails>> getAllAppointmentsByPatientId(@PathVariable("patientId") Long patientId) throws HMSException {
        return new ResponseEntity<>(appointmentService.getAllAppointmentsByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/getAllDoctor/{doctorId}")
    public ResponseEntity<List<AppointmentDetails>> getAllAppointmentsByDoctorId(@PathVariable Long doctorId) throws HMSException {
        return new ResponseEntity<>(appointmentService.getAllAppointmentsByDoctorId(doctorId), HttpStatus.OK);
    }

    @GetMapping("/countBypatinet/{patientId}")
    public ResponseEntity<List<MonthlyVisitDTO>> countAppointmentsByPatientId(@PathVariable Long patientId) throws HMSException {
        return new ResponseEntity<>(appointmentService.getAppointmentCountByMonthForPatient(patientId), HttpStatus.OK);
    }

    @GetMapping("/countByDoctor/{doctorId}")
    public ResponseEntity<List<MonthlyVisitDTO>> countAppointmentsByDoctorId(@PathVariable Long doctorId) throws HMSException {
        return new ResponseEntity<>(appointmentService.getAppointmentCountByMonthForDoctor(doctorId), HttpStatus.OK);
    }

    @GetMapping("/countReasonByPatient/{patientId}")
    public ResponseEntity<List<ReasonCountDTO>> countAppointmentReasonByPatientId(@PathVariable Long patientId) throws HMSException {
        return new ResponseEntity<>(appointmentService.getReasonCountByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/countReasonByDoctor/{doctorId}")
    public ResponseEntity<List<ReasonCountDTO>> getReasonByDoctor(@PathVariable Long doctorId) throws HMSException {
        return new ResponseEntity<>(appointmentService.getReasonCountByDoctor(doctorId), HttpStatus.OK);
    }

    @GetMapping("/countReason")
    public ResponseEntity<List<ReasonCountDTO>> getReasonByDoctor() throws HMSException {
        return new ResponseEntity<>(appointmentService.getReasonCount(), HttpStatus.OK);
    }

    @GetMapping("/getMedicineByPatient/{patientId}")
    public ResponseEntity<List<MedicineDTO>> getMedicinesByPatientId(@PathVariable Long patientId) throws HMSException {
        return new ResponseEntity<>(prescriptionService.getMedictionByPatinetId(patientId), HttpStatus.OK);
    }

    @GetMapping("/visitCount")
    public ResponseEntity<List<MonthlyVisitDTO>> getVisitCount() throws HMSException {
        return new ResponseEntity<>(appointmentService.getAppointmentCount(), HttpStatus.OK);
    }

    @GetMapping("/getTodayAppointments")
    public ResponseEntity<List<AppointmentDetails>> getTodayAppointments() throws HMSException {
        return new ResponseEntity<>(appointmentService.getTodaysAppointments(), HttpStatus.OK);
    }
}
