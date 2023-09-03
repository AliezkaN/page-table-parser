package com.aliezkan.pageparser.controller;

import com.aliezkan.pageparser.dto.out.ErrorMessage;
import com.aliezkan.pageparser.exception.TableNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler({TableNotFoundException.class, WebExchangeBindException.class})
    public ErrorMessage handleException(@NotNull ServerWebExchange serverWebExchange, Exception ex) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        HttpStatus httpStatus = responseStatus != null ? responseStatus.value() : HttpStatus.OK;

        ErrorMessage errorMessage = new ErrorMessage();

        if (ex instanceof TableNotFoundException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage.setMessage(ex.getMessage());
        } else if (ex instanceof WebExchangeBindException) {
            WebExchangeBindException exception = (WebExchangeBindException) ex;
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage.setMessage("Validation Exception");
            errorMessage.setValidationErrors(
                    exception.getFieldErrors()
                            .stream()
                            .map(x -> new AbstractMap.SimpleEntry<>(x.getField(), x.getDefaultMessage()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }

        errorMessage.setCode(httpStatus.value());
        errorMessage.setTimestamp(LocalDateTime.now());
        serverWebExchange.getResponse().setStatusCode(httpStatus);
        return errorMessage;
    }

}
