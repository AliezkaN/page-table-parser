package com.aliezkan.pageparser.dto.in;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
public class PageParserRequest {

    @NotBlank
    @URL
    private String url;

    private boolean singleTable = false;
}
