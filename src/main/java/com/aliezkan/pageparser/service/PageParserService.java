package com.aliezkan.pageparser.service;

import com.aliezkan.pageparser.exception.TableNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class PageParserService {

    private final HtmlFetchService htmlFetchService;

    public Mono<String> parse(@NonNull String url, boolean singleTable, String authorizationToken) {
        return htmlFetchService.fetchHtml(url, authorizationToken)
                .map(this::getTableFromHtml)
                .map(elements -> formatTableWithStyles(elements, singleTable))
                .filter(StringUtils::isNotBlank)
                .switchIfEmpty(Mono.error(new TableNotFoundException()))
                .cache();
    }

    private Elements getTableFromHtml(String html) {
        Document document = Jsoup.parse(html);
        return document.select("table");
    }

    private String formatTableWithStyles(Elements tableElements, boolean singleTable) {

        return tableElements.stream()
                .limit(singleTable ? 1 : tableElements.size())
                .peek(tableElement -> {
                    tableElement.attr("style", "border-collapse: collapse; width: 100%; margin-bottom: 15px");
                    Elements tableHeaders = tableElement.select("th");
                    Elements tableCells = tableElement.select("td");
                    tableHeaders.attr("style", "border: 1px solid #ddd; background-color: #f2f2f2; padding: 8px; text-align: left;");
                    tableCells.attr("style", "border: 1px solid #ddd; padding: 8px; text-align: left;");
                    Elements anchorElements = tableElement.select("a");
                    anchorElements.removeAttr("href");
                })
                .collect(Collectors.collectingAndThen(Collectors.toList(), Elements::new))
                .toString();
    }
}
