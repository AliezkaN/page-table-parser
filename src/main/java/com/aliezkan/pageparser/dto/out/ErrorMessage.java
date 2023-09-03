package com.aliezkan.pageparser.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorMessage {
    private Integer code;
    private String message;
    private Map<String, String> validationErrors;
    private LocalDateTime timestamp;
}
