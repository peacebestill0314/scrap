package com.wemake.scrap.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 문자열 유틸 클래스
 */
public class StringUtil {

    /**
     * null, "" 을 체크 한다.
     *
     * @param text 문자열을 입력 받는다
     * @return null or "" 일경우 true
     */
    public static boolean isEmpty(String text) {
        return text == null || "".equals(text);
    }

    /**
     * string 문자열을 List<String> 으로 변환한다.
     *
     * @param text
     * @return List<String>
     */
    public static List<String> stringToList(String text) {
        return new ArrayList<>(Arrays.asList(text.split(RegixUtil.EMPTY)));
    }

    /**
     * 숫자를 정렬하여 반환한다.
     *
     * @param text
     * @return String
     */
    public static String sortByNaturalOrder(String text) {
        return StringUtil.stringToList(text)
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(RegixUtil.EMPTY));
    }

    /**
     * 문자를 정렬하여 반환한다.
     *
     * @param text
     * @return String
     */
    public static String sortByComparator(String text) {
        return StringUtil.stringToList(text)
                .stream()
                .sorted(getStringComparator())
                .collect(Collectors.joining(RegixUtil.EMPTY));
    }

    /**
     * 문자열 정렬 기준을 반환한다.
     *
     * @return Comparator<String>
     */
    private static Comparator<String> getStringComparator() {
        return (o1, o2) -> {
            int result = o1.compareToIgnoreCase(o2);
            return (result == 0) ? o1.compareTo(o2) : result;
        };
    }
}
