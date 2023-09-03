package com.aliezkan.pageparser.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ServiceException extends RuntimeException {

    private HttpStatus httpStatus;

    public ServiceException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
