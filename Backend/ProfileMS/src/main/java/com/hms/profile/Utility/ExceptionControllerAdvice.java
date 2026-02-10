package com.hms.profile.Utility;

import com.hms.profile.Exception.HMSException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;



@RestControllerAdvice
public class ExceptionControllerAdvice {
    @Autowired
    Environment environment;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleException(Exception e) {
        ErrorInfo error=new ErrorInfo("Some Error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HMSException.class)
    public ResponseEntity<ErrorInfo> HmsExceptionHandler(Exception e) {
        ErrorInfo error=new ErrorInfo(environment.getProperty(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> handleValidationException(Exception e){
        String errorMsg;
        if(e instanceof MethodArgumentNotValidException){
            errorMsg = ((MethodArgumentNotValidException) e).getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
        } else {
          ConstraintViolationException ex = (ConstraintViolationException) e;
            errorMsg = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        }
        ErrorInfo error=new ErrorInfo(errorMsg, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
