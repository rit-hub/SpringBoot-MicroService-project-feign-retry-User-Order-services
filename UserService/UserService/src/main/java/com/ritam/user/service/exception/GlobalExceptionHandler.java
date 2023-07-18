package com.ritam.user.service.exception;

import com.ritam.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFroundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFroundException ex){
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).success(false).build();
        return  new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
}
