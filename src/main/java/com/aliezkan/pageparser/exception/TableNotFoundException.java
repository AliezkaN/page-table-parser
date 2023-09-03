package com.aliezkan.pageparser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TableNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Table tag in html response in provided url is not found";

    public TableNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TableNotFoundException(String message) {
        super(message);
    }
}
