package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ResponseError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        ResponseError responseError = new ResponseError();
        responseError.setTimeStamp(System.currentTimeMillis());
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());

        List<ObjectError> allErrors = exception.getAllErrors();
        StringBuilder sb = new StringBuilder();
        allErrors.forEach(objectError -> sb.append(objectError.getDefaultMessage()).append(System.lineSeparator()));

        responseError.setMessage(sb.toString());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler
    public ResponseEntity<ResponseError> handleGenericException(Exception exception) {
        ResponseError responseError = new ResponseError();
        responseError.setTimeStamp(System.currentTimeMillis());
        responseError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.setMessage(exception.getMessage());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);

    }
}
