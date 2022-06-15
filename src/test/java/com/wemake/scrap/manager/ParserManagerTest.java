package com.wemake.scrap.manager;

import com.wemake.scrap.domain.Letter;
import com.wemake.scrap.domain.ScrapResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserManagerTest {

    @Test
    void 필터_성공() {
        //given
        Letter letter = new Letter("AaBbCcDdEe", "12345");

        //when
        Letter result = ParserManager.filter("abcde12345!@$%ABCDE");

        //then
        assertEquals(letter, result);
    }

    @Test
    void 정렬_성공() {
        //given
        Letter letter = new Letter("AaBbCcDdEe", "12345");

        //when
        String result = ParserManager.cross(letter);

        //then
        assertEquals("A1a2B3b4C5cDdEe", result);
    }

    @Test
    void 묶음_성공() {
        //given
        ScrapResult result = ParserManager.bind("A1a2B3b4C5cDdEe", 6);

        //when
        ScrapResult letter = new ScrapResult("A1a2B3b4C5cD", "dEe");

        //then
        assertEquals(letter, result);
    }

}