package com.hms.appointment.Service.IMPL;

import com.hms.appointment.DTO.ApRecordDTO;

import com.hms.appointment.DTO.DoctorName;
import com.hms.appointment.DTO.RecordDetails;
import com.hms.appointment.Exception.HMSException;
import com.hms.appointment.Modal.ApRecord;
import com.hms.appointment.Repository.ApRecordRepository;
import com.hms.appointment.Repository.AppointmentRepository;
import com.hms.appointment.Service.ApRecordService;
import com.hms.appointment.Service.PrescriptionService;

import com.hms.appointment.Utility.StringListConverter;
import com.hms.appointment.clients.ProfileClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ApRecordServiceIMPL implements ApRecordService {

    private final ApRecordRepository apRecordRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionService prescriptionService;
    private final ProfileClient profileClient;


    @Override
    public Long createApRecord(ApRecordDTO request) throws HMSException {

        if (!appointmentRepository.existsById(request.getAppointmentId())) {
            throw new HMSException("APPOINTMENT_NOT_FOUND");
        }

        if (apRecordRepository.existsByAppointmentId(request.getAppointmentId())) {
            throw new HMSException("APPOINTMENT_RECORD_ALREADY_EXISTS");
        }

        ApRecord saved = apRecordRepository.save(request.toEntity());

        if (request.getPrescription() != null) {
            request.getPrescription().setAppointmentId(request.getAppointmentId());
            prescriptionService.savePrescription(request.getPrescription());
        }

        return saved.getId();
    }


    @Override
    public void updateRecord(ApRecordDTO request) throws HMSException {
        ApRecord existRecord = apRecordRepository.findById(request.getId())
                .orElseThrow(() -> new HMSException("APPOINTMENT_RECORD_NOT_FOUND"));

        existRecord.setNotes(request.getNotes());
        existRecord.setDiagnosis(request.getDiagnosis());
        existRecord.setFollowUpDate(request.getFollowUpDate());

        existRecord.setSymptoms(StringListConverter.convertListToString(request.getSymptoms()));
        existRecord.setTests(StringListConverter.convertListToString(request.getTests()));
        existRecord.setReferral(request.getReferral());

        apRecordRepository.save(existRecord);
    }

    @Override
    public ApRecordDTO getRecordByAppointmentId(Long appointmentId) throws HMSException {
        return apRecordRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new HMSException("APPOINTMENT_RECORD_NOT_FOUND"))
                .toDTO();
    }

    @Override
    public ApRecordDTO getApRecordDetailsByAppointmentId(Long appointmentId) throws HMSException {
        ApRecordDTO record = apRecordRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new HMSException("APPOINTMENT_RECORD_NOT_FOUND"))
                .toDTO();
        record.setPrescription(prescriptionService.getPrescriptionByAppointmentId(appointmentId));
        return record;

    }

    @Override
    public ApRecordDTO getRecordById(Long appointmentId) throws HMSException {
        return apRecordRepository.findById(appointmentId).orElseThrow(() -> new HMSException("APPOINTMENT_RECORD_NOT_FOUND")).toDTO();
    }

    @Override
    public List<RecordDetails> getRecordsByPatientId(Long patientId) throws HMSException {
        List<ApRecord> records = apRecordRepository.findByPatientId(patientId);
        List<RecordDetails> recordDetails = records.stream().map(ApRecord::toRecordDetails).toList();
        List<Long> doctorIds = recordDetails.stream().map(RecordDetails::getDoctorId).distinct().toList();
        List<DoctorName> doctors = profileClient.getDoctorsById(doctorIds);

        Map<Long, String> doctorMap = doctors.stream().collect(
                Collectors.toMap(DoctorName::getId, DoctorName::getName)
        );
        recordDetails.forEach(record -> {
            String doctorName = doctorMap.get(record.getDoctorId());
            if (doctorName != null) {
                record.setDoctorName(doctorName);
            } else {
                record.setDoctorName("Unknown Doctor");
            }
        });
        return recordDetails;
    }

    @Override
    public Boolean isRecordExists(Long appointmentId) throws HMSException {
        return apRecordRepository.existsByAppointmentId(appointmentId);
    }
}
