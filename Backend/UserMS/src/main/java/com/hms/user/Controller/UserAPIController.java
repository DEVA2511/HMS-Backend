package com.hms.user.Controller;

import com.hms.user.DTO.LoginDTO;
import com.hms.user.DTO.RegistrationCountDTO;
import com.hms.user.DTO.ResponseDTO;
import com.hms.user.DTO.UserDTO;
import com.hms.user.Exception.HMSException;
import com.hms.user.Service.UserService;
import com.hms.user.jwt.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Validated
@CrossOrigin
public class UserAPIController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws HMSException {
        userService.registerUser(userDTO);

        return new ResponseEntity<>(new ResponseDTO("Account created successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) throws HMSException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new HMSException("INVALID_CREDENTIALS");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("registrationCounts")
    public ResponseEntity<RegistrationCountDTO> getRegistrationCounts() throws HMSException {
        return new ResponseEntity<>(userService.getMonthlyRegistrationCountDTO(), HttpStatus.OK);
    }

   }
