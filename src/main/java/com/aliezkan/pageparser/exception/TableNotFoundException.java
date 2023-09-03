package com.aliezkan.pageparser.exception;

public class TableNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Table tag in html response in provided url is not found";

    public TableNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TableNotFoundException(String message) {
        super(message);
    }
}
