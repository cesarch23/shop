package com.edu.shop.exception.policy;

import org.springframework.http.HttpStatus;

public interface BusinessExceptionPolicy extends ExceptionPolicy{
    HttpStatus getHttpStatus();
}