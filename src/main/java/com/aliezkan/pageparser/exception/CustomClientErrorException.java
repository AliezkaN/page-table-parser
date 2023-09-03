package com.aliezkan.pageparser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class CustomClientErrorException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Unexpected client error";

    public CustomClientErrorException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomClientErrorException(String customErrorMessage) {
        super(customErrorMessage);
    }
}
