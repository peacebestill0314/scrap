package com.wemake.scrap.manager;

import com.wemake.scrap.domain.Letter;
import com.wemake.scrap.domain.ScrapResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * parser manager 테스트 클래스
 */
@SpringBootTest
class ParserManagerTest {

    @DisplayName("주어진 문자열에서 숫자와 알파벳으로 분리 검증")
    @ParameterizedTest
    @CsvSource({"AaBbCcDdEe, 12345, abcde12345!@$%ABCDE", "AaBbC, 123456789, a1234b!@$%B56A78C9"})
    void 필터_테스트(String expectedAlphabet, String expectedNum, String testText) {
        //given
        Letter expected = new Letter(expectedAlphabet, expectedNum);

        //when
        Letter result = ParserManager.filter(testText);

        //then
        assertEquals(expected, result);
    }

    @DisplayName("주어진 문자열에서 알파벳, 숫자 순으로 교차 검증")
    @ParameterizedTest
    @CsvSource({"A1a2B3b4C5cDdEe, AaBbCcDdEe, 12345"})
    void 교차_테스트(String expected, String testAlphabet, String testNum) {
        //given
        Letter letter = new Letter(testAlphabet, testNum);

        //when
        String result = ParserManager.cross(letter);

        //then
        assertEquals(expected, result);
    }

    @DisplayName("주어진 문자열에서 몫과 나머지 검증")
    @ParameterizedTest
    @CsvSource({"A1a2B3b4C5, cD, A1a2B3b4C5cD, 5"})
    void 묶음_테스트(String expectedQuotient, String expectedRemainder, String testText, Integer testUnit) {
        //given
        ScrapResult expected = new ScrapResult(expectedQuotient, expectedRemainder);

        //when
        ScrapResult result = ParserManager.divide(testText, testUnit);

        //then
        assertEquals(expected, result);
    }

}