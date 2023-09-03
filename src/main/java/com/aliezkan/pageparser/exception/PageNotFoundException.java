package com.aliezkan.pageparser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Page not found! please provide proper url";

    public PageNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public PageNotFoundException(String message) {
        super(message);
    }
}
