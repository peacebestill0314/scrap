package com.wemake.scrap.manager;

import com.google.common.collect.Iterables;
import com.wemake.scrap.common.RegixUtil;
import com.wemake.scrap.common.StringUtil;
import com.wemake.scrap.domain.Letter;
import com.wemake.scrap.domain.ScrapResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 데이터를 파싱하는 클래스
 * - 필터, 정렬, 교차, 묶음
 */
@Slf4j
public class ParserManager {

    /**
     * 문자열에서 대소문자, 숫자를 필터링 한다.
     */
    public static Letter filter(String text) {
        String alphabet = text.replaceAll(RegixUtil.ALPHABET, RegixUtil.EMPTY);
        String number = text.replaceAll(RegixUtil.NUMBER, RegixUtil.EMPTY);
        return sort(alphabet, number);
    }

    /**
     * 알파벳과 숫자를 정렬 한다.
     */
    public static Letter sort(String alphabet, String number) {
        String SortedAlphabet = StringUtil.sortByComparator(alphabet);
        String SortedNumber = StringUtil.sortByNaturalOrder(number);
        return Letter.of(SortedAlphabet, SortedNumber);
    }

    /**
     * 알파벳과, 숫자를 교차 한다.
     */
    public static String cross(Letter letter) {
        int alphabetSize = letter.alphabet().length();
        int numberSize = letter.number().length();
        int maxSize = Math.max(alphabetSize, numberSize);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxSize; i++) {
            sb.append(Iterables.get(StringUtil.stringToList(letter.alphabet()), i, RegixUtil.EMPTY));
            sb.append(Iterables.get(StringUtil.stringToList(letter.number()), i, RegixUtil.EMPTY));
        }
        log.debug("\n 교차 결과 : \n {}", sb);
        return sb.toString();
    }

    /**
     * 출력 묶음 단위로 몫과 나머지를 구한다.
     */
    public static ScrapResult bind(String text, int unit) {
        int textSize = text.length();
        int bindSize = textSize - Math.floorMod(textSize, unit);
        return ScrapResult.builder()
                .quotient(text.substring(0, bindSize))
                .remainder(text.substring(bindSize, textSize))
                .build();
    }

}
