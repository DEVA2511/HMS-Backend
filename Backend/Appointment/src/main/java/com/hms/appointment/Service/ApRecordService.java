package com.hms.appointment.Service;

import com.hms.appointment.DTO.ApRecordDTO;
import com.hms.appointment.DTO.RecordDetails;
import com.hms.appointment.Exception.HMSException;
import com.hms.appointment.Modal.ApRecord;

import java.util.List;

public interface ApRecordService {
    Long createApRecord(ApRecordDTO request) throws HMSException;

    public void updateRecord(ApRecordDTO request) throws HMSException;

    ApRecordDTO getRecordByAppointmentId(Long appointmentId) throws HMSException;

    public ApRecordDTO getApRecordDetailsByAppointmentId(Long appointmentId) throws HMSException;

    ApRecordDTO getRecordById(Long appointmentId) throws HMSException;

    List<RecordDetails> getRecordsByPatientId(Long patientId) throws HMSException;

    Boolean isRecordExists(Long appointmentId) throws HMSException;
}
