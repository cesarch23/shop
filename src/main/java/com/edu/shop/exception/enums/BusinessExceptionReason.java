package com.edu.shop.exception.enums;

import com.edu.shop.exception.policy.BusinessExceptionPolicy;
import org.springframework.http.HttpStatus;

public enum BusinessExceptionReason implements BusinessExceptionPolicy {

    CLIENT_WITH_EMAIL_EXITS("El cliente con el email %s ya existe", HttpStatus.NOT_FOUND),
    CLIENT_NOT_FOUND ( "El cliente con email %s no existe", HttpStatus.NOT_FOUND);
    //TODO: deben coincidir con la cantidad y tipo de parámetros del contrsuctor
    //TODO  declaracion de constantes con parametros
    //TODO: o "enum con constructores y atributos"
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    //se inicializa automaticament
    BusinessExceptionReason(String message, HttpStatus httpStatus) {
        this.code = BusinessExceptionReason.class.getSimpleName();// el cod retorar el nombre de la clase en tiempo de ejecucion
        this.message = message;
        this.httpStatus = httpStatus;

        /*
         * enum constructors are used to initialize the constants with specific values.
         * These constructors are implicitly private and cannot be invoked directly;
         * they are called automatically when each enum constant is created.
         * */
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}