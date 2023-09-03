package com.aliezkan.pageparser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenClientException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Do not have permission to access the requested resource.";

    public ForbiddenClientException() {
        super(DEFAULT_MESSAGE);
    }

    public ForbiddenClientException(String message) {
        super(message);
    }
}
