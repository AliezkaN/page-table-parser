package com.aliezkan.pageparser.service;

import com.aliezkan.pageparser.exception.TableNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Validated
public class PageParserService {

    private final HtmlFetchService htmlFetchService;
    public Mono<String> parse(@NonNull String url) {
        return htmlFetchService.fetchHtml(url)
                .map(this::getTableFromHtml)
                .map(this::formatTableWithStyles)
                .filter(StringUtils::isNotBlank)
                .switchIfEmpty(Mono.error(new TableNotFoundException()))
                .cache();
    }

    private Elements getTableFromHtml(String html) {
        Document document = Jsoup.parse(html);
        return document.select("table");
    }

    private String formatTableWithStyles(Elements tableElements) {
        if (tableElements.size() > 0) {
            Element table = tableElements.get(0);

            table.attr("class", "beautiful-table");
            table.attr("style", "border-collapse: collapse; width: 100%;");

            Elements tableHeaders = table.select("th");
            Elements tableCells = table.select("td");

            tableHeaders.attr("style", "background-color: #f2f2f2; padding: 8px; text-align: left;");
            tableCells.attr("style", "border: 1px solid #ddd; padding: 8px; text-align: left;");
        }

        return tableElements.toString();
    }
}
