package com.aliezkan.pageparser.service;

import com.aliezkan.pageparser.exception.TableNotFoundException;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class PageParserServiceTest {

    @Mock
    private HtmlFetchService htmlFetchService;
    @InjectMocks
    private PageParserService pageParserService;

    @TestFactory
    List<DynamicNode> test() {
        return List.of(
                DynamicTest.dynamicTest("expect table html tag", () -> {
                    when(htmlFetchService.fetchHtml(Util.URL_WITH_TABLE)).thenReturn(Mono.just(Util.HTML_BODY_WITH_TABLE));
                    Mono<String> result = pageParserService.parse(Util.URL_WITH_TABLE, true);
                    StepVerifier.create(result)
                            .expectNext(Util.PARSER_RESPONSE)
                            .verifyComplete();
                }),
                DynamicTest.dynamicTest("expect TableNotFoundException thrown", () -> {
                    when(htmlFetchService.fetchHtml(Util.URL_WITHOUT_TABLE)).thenReturn(Mono.just(Util.HTML_BODY_WITHOUT_TABLE));
                    Mono<String> result = pageParserService.parse(Util.URL_WITHOUT_TABLE, true);
                    StepVerifier.create(result)
                            .expectError(TableNotFoundException.class)
                            .verify();
                })
        );
    }

    @UtilityClass
    public class Util{
        private static final String URL_WITH_TABLE = "http://example.com";
        private static final String URL_WITHOUT_TABLE = "http://example1.com";
        private static final String HTML_BODY_WITH_TABLE = "<html><body><table></table></body></html>";
        private static final String HTML_BODY_WITHOUT_TABLE = "<html><body></body></html>";
        private static final String PARSER_RESPONSE = "<table style=\"border-collapse: collapse; width: 100%; margin-bottom: 15px\"></table>";
    }
}
