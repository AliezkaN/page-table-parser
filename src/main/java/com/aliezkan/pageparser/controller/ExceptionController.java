package com.aliezkan.pageparser.controller;

import com.aliezkan.pageparser.dto.out.ErrorMessage;
import com.aliezkan.pageparser.exception.TableNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler({TableNotFoundException.class})
    public ErrorMessage handleException(@NotNull ServerWebExchange serverWebExchange, Exception ex) {
        System.out.println(ex);
        return new ErrorMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public ErrorMessage handleValidationExceptions(WebExchangeBindException ex) {
        Map<String, String> validationErrors = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setValidationErrors(validationErrors);
        errorMessage.setTimestamp(LocalDateTime.now());
        return errorMessage;
    }
}
