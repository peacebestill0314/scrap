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

    @DisplayName("서비스 로직의 응답 데이터를 검증")
    @ParameterizedTest
    @CsvSource({"https://peacebestill.oopy.io, text, 10"})
    void scrap_성공(String url, String type, Integer unit) throws Exception {

        //given
        ScrapRequest request = new ScrapRequest();
        request.setUrl(url);
        request.setType(type);
        request.setUnit(unit);

        ScrapResult expected = ScrapResult.builder()
                .quotient("A0A0A0a0a0a0a0a1b1b1b2c2c2c2c3c3c3D3d4d4d5d6d6e8e9e9eeeeeeefgghiiiiiiiiiiiiiJJkLllllmmNnnnnnooooPPppppppppQrrrrrSSSSsssttttttuuuuuuuvvxxxyyz")
                .remainder("z")
                .build();

        //when
        ScrapResult result = scrapService.scrap(request);

        //then
        assertEquals(expected, result);
    }
}
