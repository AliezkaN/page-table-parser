package com.aliezkan.pageparser.dto.in;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
public class PageParserRequest {

    @NotBlank(message = "url must not be blank")
    @URL
    private String url;
}
