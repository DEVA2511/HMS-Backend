package com.hms.appointment.Service.IMPL;

import com.hms.appointment.DTO.DoctorName;
import com.hms.appointment.DTO.MedicineDTO;
import com.hms.appointment.DTO.PrescriptionDTO;
import com.hms.appointment.DTO.PrescriptionDetails;
import com.hms.appointment.Exception.HMSException;
import com.hms.appointment.Modal.Appointment;
import com.hms.appointment.Modal.Prescription;
import com.hms.appointment.Repository.ApRecordRepository;
import com.hms.appointment.Repository.AppointmentRepository;
import com.hms.appointment.Repository.PrescriptionRepository;
import com.hms.appointment.Service.MedicineService;
import com.hms.appointment.Service.PrescriptionService;
import com.hms.appointment.clients.ProfileClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceIMPL implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicineService medicineService;
    private final ProfileClient profileClient;
    private final ApRecordRepository apRecordRepository;

    @Override
    public Long savePrescription(PrescriptionDTO request) throws HMSException {

        long prescriptionId = prescriptionRepository.save(request.toEntity()).getId();
        request.getMedicines().forEach(medicine -> {
            medicine.setPrescriptionId(prescriptionId);
        });
        medicineService.saveAllMedicine(request.getMedicines());
        return prescriptionId;
    }


    @Override
    public PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId) throws HMSException {
        PrescriptionDTO prescriptionDTO = prescriptionRepository.findByAppointment_Id(appointmentId)
                .orElseThrow(() -> new HMSException("PRESCRIPTION_NOT_FOUND"))
                .toDTO();
        prescriptionDTO.setMedicines(medicineService.getAllMedicineByPrescriptionId(prescriptionDTO.getId()));
        return prescriptionDTO;
    }

    @Override
    public PrescriptionDTO getPrescriptionById(Long prescriptionId) throws HMSException {
        PrescriptionDTO prescriptionDTO = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new HMSException("PRESCRIPTION_NOT_FOUND"))
                .toDTO();
        prescriptionDTO.setMedicines(medicineService.getAllMedicineByPrescriptionId(prescriptionDTO.getId()));
        return prescriptionDTO;
    }


    @Override
    public List<PrescriptionDetails> getPrescriptionsByPatientId(Long patientId) throws HMSException {
        List<Prescription> prescriptions = prescriptionRepository.findAllByPatientId(patientId);

        List<PrescriptionDetails> prescriptionDetails = prescriptions.stream()
                .map(Prescription::tpDetails)
                .toList();

        // Attach medicines
        prescriptionDetails.forEach(details -> {

            try {
                // medicines
                details.setMedicines(
                        medicineService.getAllMedicineByPrescriptionId(details.getId())
                );
            } catch (HMSException e) {
                throw new RuntimeException(e);
            }
            apRecordRepository.findByAppointmentId(details.getAppointmentId())
                    .ifPresent(apRecord ->
                            details.setDiagnosis(apRecord.getDiagnosis())
                    );
        });

        // Collect doctor IDs
        List<Long> doctorIds = prescriptionDetails.stream()
                .map(PrescriptionDetails::getDoctorId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        // Fetch doctor names
        List<DoctorName> doctorNames = profileClient.getDoctorsById(doctorIds);

        Map<Long, String> doctorMap = doctorNames.stream()
                .collect(Collectors.toMap(DoctorName::getId, DoctorName::getName));

        // Attach doctor names
        prescriptionDetails.forEach(record -> {
            String doctorName = doctorMap.get(record.getDoctorId());
            record.setDoctorName(doctorName != null ? doctorName : "Doctor Unknown");
        });


        return prescriptionDetails;
    }

    @Override
    public List<PrescriptionDetails> getPrescriptions() throws HMSException {
        List<Prescription> prescriptions = (List<Prescription>) prescriptionRepository.findAll();
        List<PrescriptionDetails> prescriptionDetails = prescriptions.stream()
                .map(Prescription::tpDetails)
                .toList();

        // Collect doctor IDs
        List<Long> doctorIds = prescriptionDetails.stream()
                .map(PrescriptionDetails::getDoctorId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        List<Long> patientIds = prescriptionDetails.stream().map(PrescriptionDetails::getPatientId).distinct().toList();

        // Fetch doctor names
        List<DoctorName> doctorNames = profileClient.getDoctorsById(doctorIds);
        List<DoctorName> patientNames = profileClient.getPatientsById(patientIds);


        Map<Long, String> doctorMap = doctorNames.stream()
                .collect(Collectors.toMap(DoctorName::getId, DoctorName::getName));
        Map<Long, String> patientMap = patientNames.stream()
                .collect(Collectors.toMap(DoctorName::getId, DoctorName::getName));

        prescriptionDetails.forEach(record -> {
            String doctorName = doctorMap.get(record.getDoctorId());
            String patientName = patientMap.get(record.getPatientId());
            record.setPatientName(patientName != null ? patientName : "Patient Unknown");
            record.setDoctorName(doctorName != null ? doctorName : "Doctor Unknown");
        });


        return prescriptionDetails;
    }

    @Override
    public List<MedicineDTO> getMedictionByPatinetId(Long patientId) throws HMSException {
        List<Long> prescriptionIds = prescriptionRepository.findAllPrescriptionIdsByPatientId(patientId);
        return medicineService.getMedicinesByPrescriptionId(prescriptionIds);
    }

}
