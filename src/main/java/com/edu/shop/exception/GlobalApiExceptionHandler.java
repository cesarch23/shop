package com.edu.shop.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception){

        ErrorResponse errorResponse = new ErrorResponse(
                exception.getCode(),
                exception.getMessage(),
                exception.getHttpStatus()!= null ? exception.getHttpStatus().value() : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
                //TODO ADD INVALID PARAMETERS
        );
        return new ResponseEntity<>(errorResponse,exception.httpStatus);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception ){
        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL SERVER ERROR",
                "Ocurrio un error inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
