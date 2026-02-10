package com.hms.user.Service;


import com.hms.user.DTO.RegistrationCountDTO;
import com.hms.user.DTO.UserDTO;
import com.hms.user.Exception.HMSException;

public interface UserService {
    public void registerUser(UserDTO userDTO) throws HMSException;
    public UserDTO loginUser(UserDTO userDTO) throws HMSException;
    public UserDTO getIUserById(Long id) throws HMSException;
    public void updateUser(UserDTO userDTO);
    public UserDTO getUserByName(String email) throws HMSException;

    RegistrationCountDTO getMonthlyRegistrationCountDTO();
}
