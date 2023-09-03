package com.aliezkan.pageparser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedClientException extends ServiceException{

    private static final String DEFAULT_EXCEPTION = "Unauthorized to fetch resource";

    public UnauthorizedClientException() {
        super(DEFAULT_EXCEPTION);
    }

    public UnauthorizedClientException(String message) {
        super(message);
    }

}
