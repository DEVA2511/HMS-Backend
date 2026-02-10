package com.hms.user.jwt;

import com.hms.user.DTO.UserDTO;
import com.hms.user.Exception.HMSException;
import com.hms.user.Service.UserService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        try{
            UserDTO dto= userService.getUserByName(email);
            return new CustomUserDetails(
                    dto.getId(),
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getRole(),
                    dto.getName(),
                    dto.getProfileId()
            );
        } catch (HMSException e) {
           e.printStackTrace();
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
