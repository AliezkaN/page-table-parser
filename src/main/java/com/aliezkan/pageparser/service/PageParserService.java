package com.aliezkan.pageparser.service;

import com.aliezkan.pageparser.exception.TableNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Validated
public class PageParserService {

    private final HtmlFetchService htmlFetchService;
    private static final Pattern TABLE_PATTERN = Pattern.compile("<table[^>]*>(.*?)</table>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    public Mono<String> parse(@NonNull String url) {
        return htmlFetchService.fetchHtml(url)
                .map(html -> {
                    Matcher matcher = TABLE_PATTERN.matcher(html);
                    return matcher.find() ? matcher.group(0) : "";
                })
                .filter(StringUtils::isNotBlank)
                .switchIfEmpty(Mono.error(new TableNotFoundException()))
                .cache();
    }
}
