package com.hms.user.Service;

import com.hms.user.DTO.Roles;
import com.hms.user.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {
    @Autowired
    private WebClient.Builder webClient;

    public Mono<Long> addProfile(UserDTO userDTO) {
        if(userDTO.getRole().equals(Roles.DOCTOR)){
            return webClient.build()
                    .post()
                    .uri("http://localhost:8083/api/profile/doctor/createDoctor")
                    .bodyValue(userDTO)
                    .retrieve()
                    .bodyToMono(Long.class);
        } else if(userDTO.getRole().equals(Roles.PATIENT)){
            return webClient.build()
                    .post()
                    .uri("http://localhost:8083/api/profile/patient/createPatient")
                    .bodyValue(userDTO)
                    .retrieve()
                    .bodyToMono(Long.class);
        } else {
            return Mono.empty();
        }

    }
}
