package com.aliezkan.pageparser.exception;

import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
