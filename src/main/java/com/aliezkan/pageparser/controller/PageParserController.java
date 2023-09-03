package com.aliezkan.pageparser.controller;

import com.aliezkan.pageparser.dto.in.PageParserRequest;
import com.aliezkan.pageparser.service.PageParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/page-parser")
@Validated
public class PageParserController {

    private final PageParserService service;

    @GetMapping(value = "/parse", produces = MediaType.TEXT_HTML_VALUE)
    public Mono<String> getPage(@Valid PageParserRequest request){
        return service.parse(request.getUrl());
    }
}
