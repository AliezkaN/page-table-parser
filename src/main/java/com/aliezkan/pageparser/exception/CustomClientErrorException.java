package com.aliezkan.pageparser.exception;

import org.springframework.http.HttpStatus;

public class CustomClientErrorException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Unexpected client error";

    public CustomClientErrorException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomClientErrorException(String customErrorMessage) {
        super(customErrorMessage);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.I_AM_A_TEAPOT;
    }
}
