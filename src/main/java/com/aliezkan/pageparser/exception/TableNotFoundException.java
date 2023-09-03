package com.aliezkan.pageparser.exception;

import org.springframework.http.HttpStatus;

public class TableNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Table tag in html response in provided url is not found";

    public TableNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TableNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
