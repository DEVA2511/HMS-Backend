package com.hms.appointment.Service;

import com.hms.appointment.DTO.AppointmentDTO;
import com.hms.appointment.DTO.DoctorDTO;
import com.hms.appointment.DTO.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {
    @Autowired
    private WebClient.Builder webClient;

    public Mono<Boolean> doctorExists(Long id) {

    return webClient.build()
            .get()
            .uri("http://localhost:8083/api/profile/doctor/exists/"+ id)
            .retrieve()
            .bodyToMono(Boolean.class);
    }
    public Mono<Boolean> patientExists(Long id) {

        return webClient.build()
                .get()
                .uri("http://localhost:8083/api/profile/patient/exists/"+ id)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    public Mono<DoctorDTO> getDoctorById(Long id) {

        return webClient.build()
                .get()
                .uri("http://localhost:8083/api/profile/doctor/get/"+ id)
                .retrieve()
                .bodyToMono(DoctorDTO.class);
    }

    public Mono<PatientDTO> getPatientById(Long id) {
        return webClient.build()
                .get()
                .uri("http://localhost:8083/api/profile/patient/get/"+ id)
                .retrieve()
                .bodyToMono(PatientDTO.class);
    }
}
