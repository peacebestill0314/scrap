package com.wemake.scrap.manager;

import com.wemake.scrap.domain.ScrapType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * crawler manager 테스트 클래스
 */
@SpringBootTest
class CrawlerManagerTest {

    @DisplayName("resources/static/sub/test.html 파일로 크롤링 검증")
    @ParameterizedTest
    @CsvSource({"http://localhost:8080/stub/test.html"})
    void 크롤링_성공(String url) throws Exception {

        //given
        String expected = "<!doctype html>\n" +
                "<html lang=\"ko\"> \n" +
                " <head> \n" +
                "  <meta charset=\"UTF-8\"> \n" +
                "  <title></title> \n" +
                " </head> \n" +
                " <body>\n" +
                "   abcdeABCDE12345!@#$%知己之  \n" +
                " </body>\n" +
                "</html>";

        //when
        String result = CrawlerManager.crawl(url, ScrapType.HTML);

        //then
        assertEquals(expected, result);
    }

    @DisplayName("url에 http, https가 없는 경우 IllegalArgumentException 검증")
    @ParameterizedTest
    @CsvSource({"www.peacebestill.oopy.io"})
    void 크롤링_실패_http_https(String url) {
        //when, then
        assertThatThrownBy(() -> CrawlerManager.crawl(url, ScrapType.TEXT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(url);
    }

    @DisplayName("접속 불가한 url인 경우 UnknownHostException 검증")
    @ParameterizedTest
    @CsvSource({"http://www.peacebestill.oopy.iox, www.peacebestill.oopy.iox"})
    void 크롤링_실패_접속불가(String url, String message) {
        //when, then
        assertThatThrownBy(() -> CrawlerManager.crawl(url, ScrapType.TEXT))
                .isInstanceOf(UnknownHostException.class)
                .hasMessageContaining(message);
    }
}
