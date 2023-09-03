package com.aliezkan.pageparser.exception;

import org.springframework.http.HttpStatus;

public class PageNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Page not found! please provide proper url";

    public PageNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public PageNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
