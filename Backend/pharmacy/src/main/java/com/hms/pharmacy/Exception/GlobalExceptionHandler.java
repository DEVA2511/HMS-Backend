package com.hms.pharmacy.Exception;

import com.hms.pharmacy.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HMSException.class)
    public ResponseEntity<ResponseDTO> handleHMSException(HMSException e) {
        return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneralException(Exception e) {
        return new ResponseEntity<>(new ResponseDTO("An internal server error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
