package com.aliezkan.pageparser.controller;

import com.aliezkan.pageparser.dto.in.PageParserRequest;
import com.aliezkan.pageparser.service.PageParserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/page-parser")
@Validated
public class PageParserController {

    private final PageParserService service;

    @Operation(summary = "fetch table from webpage")
    @ApiResponse(
            responseCode = "200",
            description = "table successfully fetched",
            content = { @Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/parse", produces = MediaType.TEXT_HTML_VALUE)
    public Mono<String> getPage(@Valid PageParserRequest request,
                                @RequestHeader(value = "Authorization", required = false) String authorizationToken){
        return service.parse(request.getUrl(), request.isSingleTable(), authorizationToken);
    }
}
