package com.wemake.scrap.service;

import com.wemake.scrap.domain.ScrapRequest;
import com.wemake.scrap.domain.ScrapResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * scrap service 테스트 클래스
 */
@SpringBootTest
public class ScrapServiceTest {

    @Autowired
    ScrapService scrapService;

    @DisplayName("resources/static/sub/test.html 경로의 파일로 서비스 로직의 응답 데이터를 검증")
    @ParameterizedTest
    @CsvSource({"http://localhost:8080/stub/test.html, text, 4"})
    void scrap_성공(String url, String type, Integer unit) throws Exception {
        //given
        ScrapRequest request = new ScrapRequest();
        request.setUrl(url);
        request.setType(type);
        request.setUnit(unit);

        ScrapResult expected = ScrapResult.builder()
                .quotient("A1a2B3b4C5cD")
                .remainder("dEe")
                .build();

        //when
        ScrapResult result = scrapService.scrap(request);

        //then
        assertEquals(expected, result);
    }
}
