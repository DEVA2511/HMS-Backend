package com.hms.appointment.Controller;

import com.hms.appointment.DTO.ApRecordDTO;
import com.hms.appointment.DTO.MedicineDTO;
import com.hms.appointment.DTO.PrescriptionDetails;
import com.hms.appointment.DTO.RecordDetails;
import com.hms.appointment.Exception.HMSException;
import com.hms.appointment.Service.ApRecordService;
import com.hms.appointment.Service.MedicineService;
import com.hms.appointment.Service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment/report")
@CrossOrigin
@Validated
@RequiredArgsConstructor
public class ApRecordController {

    private final ApRecordService apRecordService;
    private final PrescriptionService prescriptionService;
    private final MedicineService medicineService;

    @PostMapping("/create")
    public ResponseEntity<Long> createAppointmentReport(@RequestBody ApRecordDTO request) throws HMSException {
        return new ResponseEntity<>(apRecordService.createApRecord(request), HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateAppointmentReport(@RequestBody ApRecordDTO request) throws HMSException {
        apRecordService.updateRecord(request);
        return new ResponseEntity<>("Appointment Record Updated", HttpStatus.OK);
    }

    @GetMapping("/get/byappointment/{appointmentId}")
    public ResponseEntity<ApRecordDTO> getByAppointmentId(@PathVariable Long appointmentId) throws HMSException {
        return new ResponseEntity<>(apRecordService.getRecordByAppointmentId(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getDetialsByAppointment/{appointmentId}")
    public ResponseEntity<ApRecordDTO> getApRecordDetailsByAppointmentId(@PathVariable Long appointmentId) throws HMSException {
        return new ResponseEntity<>(apRecordService.getApRecordDetailsByAppointmentId(appointmentId), HttpStatus.OK);
    }

//    this request used for get record by record id
    @GetMapping("/get/byid/{id}")
    public ResponseEntity<ApRecordDTO> getById(@PathVariable Long id) throws HMSException {
        return new ResponseEntity<>(apRecordService.getRecordById(id), HttpStatus.OK);
    }

    @GetMapping("/getRecordsByPatinetId/{patientId}")
    public ResponseEntity<List<RecordDetails>> getRecordsByPatientId(@PathVariable Long patientId) throws HMSException {
        return new ResponseEntity<>(apRecordService.getRecordsByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/exists/{appointmentId}")
    public ResponseEntity<Boolean> isRecordExists(@PathVariable Long appointmentId) throws HMSException {
        return new ResponseEntity<>(apRecordService.isRecordExists(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getPrescriptionByPatientId/{patientId}")
    public ResponseEntity<List<PrescriptionDetails>> getPrescriptions(@PathVariable Long patientId) throws HMSException {
        return new ResponseEntity<>(prescriptionService.getPrescriptionsByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/getAllPrescriptions")
    public ResponseEntity<List<PrescriptionDetails>> getAllPrescriptions() throws HMSException {
        return new ResponseEntity<>(prescriptionService.getPrescriptions(), HttpStatus.OK);

    }

    @GetMapping("/getMedicinesByPrescriptionId/{prescriptionId}")
    public ResponseEntity<List<MedicineDTO>> getMedicinesByPrescriptionId(@PathVariable Long prescriptionId) throws HMSException {
        return new ResponseEntity<>(medicineService.getAllMedicineByPrescriptionId(prescriptionId), HttpStatus.OK);
    }

}
