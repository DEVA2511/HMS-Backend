// java
package com.hms.user.Service.IMPL;

import com.hms.user.DTO.*;
import com.hms.user.Exception.HMSException;
import com.hms.user.Model.User;
import com.hms.user.Repository.UserRepository;
import com.hms.user.Service.UserService;
import com.hms.user.clients.ProfileClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfileClient profileClient;


    @Transactional
    public void registerUser(UserDTO userDTO) throws HMSException {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new HMSException("USER_ALREADY_EXISTS");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(userDTO.toEntity());

        Long profileId;

        try {
            if (userDTO.getRole() == Roles.PATIENT) {

                PatientDTO patientDTO = new PatientDTO(
                        savedUser.getName(),
                        savedUser.getEmail()
                );

                profileId = profileClient.addPatient(patientDTO);

            } else if (userDTO.getRole() == Roles.DOCTOR) {

                DoctorDTO doctorDTO = new DoctorDTO(
                        savedUser.getName(),
                        savedUser.getEmail()

                );

                profileId = profileClient.addDoctor(doctorDTO);

            } else {
                throw new HMSException("INVALID_ROLE");
            }

        } catch (FeignException e) {
            throw new HMSException("PROFILE_SERVICE_FAILED");
        }

        savedUser.setProfileId(profileId);
        userRepository.save(savedUser);
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) throws HMSException {
        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new HMSException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new HMSException("INVALID_CREDENTIALS");
        }
        user.setPassword(null); //hide the password
        return user.toDTO();
    }

    @Override
    public UserDTO getIUserById(Long id) throws HMSException {

        return userRepository.findById(id).orElseThrow(() -> new HMSException("USER_NOT_FOUND")).toDTO();
    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }

    @Override
    public UserDTO getUserByName(String email) throws HMSException {
        return userRepository.findByEmail(email).orElseThrow(() -> new HMSException("USER_NOT_FOUND")).toDTO();
    }

    @Override
    public RegistrationCountDTO getMonthlyRegistrationCountDTO() {
        List<MonthlyRoleCount> doctorCounts = userRepository.countRegistrationsByGroupedByMonth(Roles.DOCTOR);
        List<MonthlyRoleCount> patientCounts = userRepository.countRegistrationsByGroupedByMonth(Roles.PATIENT);
        return new RegistrationCountDTO(doctorCounts, patientCounts);
    }

}
